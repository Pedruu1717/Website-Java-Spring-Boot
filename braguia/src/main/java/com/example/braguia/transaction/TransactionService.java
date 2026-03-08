package com.example.braguia.transaction;

import com.example.braguia.account.Account;
import com.example.braguia.client.Client;
import com.example.braguia.account.AccountRepository;
import com.example.braguia.client.ClientRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.Exception;
import java.time.LocalDateTime;

import java.math.BigDecimal;

@Service
public class TransactionService {
    // Create Bean.
    @Autowired
    private final TransactionRepository transactionRepository;
    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final ClientRepository clientRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, ClientRepository clientRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public Optional<Transaction> getTransactionByType(TransactionType type) {
        return transactionRepository.findByType(type);
    }

    public Optional<Transaction> getTransactionByDestinationAccountId(Long destination_account_id) {
        return transactionRepository.findByDestinationAccountId(destination_account_id);
    }

    public Iterable<Transaction> getAllTransactions() {
        return transactionRepository.findAll(); // JSON or XML
    }

    @Transactional
    public boolean addTransaction(TransactionType type, BigDecimal value, LocalDateTime date, Long source_account_id, Long destination_account_id, Long client_id) {
        Optional<Account> source_account = accountRepository.findById(source_account_id);
        Optional<Account> destination_account = accountRepository.findById(destination_account_id);
        Optional<Client> client = clientRepository.findById(client_id);

        if (value.compareTo(BigDecimal.ZERO) > 0 && source_account.isPresent() && destination_account.isPresent() && client.isPresent()) {
            Iterable<Account> client_accounts = accountRepository.findAllByClientId(client_id);
            boolean client_owns_account = false;

            // Check if Client is the owner of the source Account
            for (Account account : client_accounts) {
                if (account.getId().equals(source_account.get().getId())) {
                    client_owns_account = true;
                    break;
                }
            }

            if (client_owns_account) {
                try {
                    switch (type) {
                        case DEPOSIT:
                            if (source_account.get().getId().equals(destination_account.get().getId())) {
                                BigDecimal src_acc_balance_after_transaction = source_account.get().getBalance().add(value);
                                source_account.get().setBalance(src_acc_balance_after_transaction);
                            } else {
                                throw new Exception("Could not deposit because source account is different from destination account");
                            }
                            break;
                        case WITHDRAWAL:
                            if (source_account.get().getId().equals(destination_account.get().getId())) {
                                BigDecimal src_acc_balance_after_transaction = source_account.get().getBalance().subtract(value);
                                if (src_acc_balance_after_transaction.compareTo(BigDecimal.ZERO) >= 0) {
                                    source_account.get().setBalance(src_acc_balance_after_transaction);
                                } else {
                                    throw new Exception("Could not withdraw because account balance is inferior than value to be withdrawn");
                                }
                            } else {
                                throw new Exception("Could not withdraw because source account is different from destination account");
                            }
                            break;
                        case TRANSFER:
                            if (!source_account.get().getId().equals(destination_account.get().getId())) {
                                BigDecimal src_acc_balance_after_transaction = source_account.get().getBalance().subtract(value);
                                if (src_acc_balance_after_transaction.compareTo(BigDecimal.ZERO) >= 0) {
                                    BigDecimal dest_acc_balance_after_transaction = destination_account.get().getBalance().add(value);
                                    source_account.get().setBalance(src_acc_balance_after_transaction);
                                    destination_account.get().setBalance(dest_acc_balance_after_transaction);
                                } else {
                                    throw new Exception("Could not transfer because source account balance is inferior than value to be transferred");
                                }
                            } else {
                                throw new Exception("Could not transfer because source account is the same as destination account");
                            }
                            break;
                        default:
                            throw new Exception("Invalid transaction type");
                    }

                    Transaction transaction = new Transaction();
                    transaction.setType(type);
                    transaction.setValue(value);
                    transaction.setDate(date);
                    transaction.setSourceAccount(source_account.get());
                    transaction.setDestinationAccount(destination_account.get());
                    transaction.setClient(client.get());
                    transactionRepository.save(transaction);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}