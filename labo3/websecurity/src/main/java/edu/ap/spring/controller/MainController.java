package edu.ap.spring.controller;

import edu.ap.spring.service.BlockChainService;
import edu.ap.spring.service.WalletService;
import edu.ap.spring.transaction.Block;
import edu.ap.spring.transaction.Transaction;
import jakarta.annotation.PostConstruct;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class MainController {
    private final BlockChainService bChain;
    private final WalletService coinbase;
    private final WalletService walletServiceA;
    private final WalletService walletServiceB;

    public MainController(BlockChainService bChain, WalletService coinbase, WalletService walletServiceA, WalletService walletServiceB) {
        this.bChain = bChain;
        this.coinbase = coinbase;
        this.walletServiceA = walletServiceA;
        this.walletServiceB = walletServiceB;
    }

    @PostConstruct
    private void init() {
        bChain.setSecurity();
        coinbase.generateKeyPair();
        walletServiceA.generateKeyPair();
        walletServiceB.generateKeyPair();

        // create genesis transaction, which sends 100 coins to walletA:
        Transaction genesisTransaction = new Transaction(coinbase.getPublicKey(), walletServiceA.getPublicKey(), 100f);
        genesisTransaction.generateSignature(coinbase.getPrivateKey());     // manually sign the genesis transaction
        genesisTransaction.transactionId = "0"; // manually set the transaction id

        // creating and Mining Genesis block
        Block genesis = new Block();
        genesis.setPreviousHash("0");
        genesis.addTransaction(genesisTransaction, bChain);
        bChain.addBlock(genesis);
    }

    @GetMapping(value = "/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping(value = "/home")
    public String home(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("user", principal.getName());
        }
        return "home";
    }

    @GetMapping(value = "/balance/{wallet}")
    public String getBalance(@PathVariable("wallet") String wallet,
                             Model model) {
        model.addAttribute("wallet", wallet);

        if (wallet.equalsIgnoreCase("walletA")) {
            model.addAttribute("balance", walletServiceA.getBalance());
        } else if (wallet.equalsIgnoreCase("walletB")) {
            model.addAttribute("balance", walletServiceB.getBalance());
        } else {
            model.addAttribute("balance", 0f);
        }

        return "balance";
    }

    @GetMapping(value = "/transaction")
    public String getForm() {
        return "transaction";
    }

    @PostMapping(value = "/transaction")
    public String transaction(@RequestParam("wallet1") String wallet1,
                              @RequestParam("wallet2") String wallet2,
                              @RequestParam("amount") float amount) {
        try {
            if (wallet1.equalsIgnoreCase("walletA") && wallet2.equalsIgnoreCase("walletB")) {
                walletServiceA.sendFunds(walletServiceB.getPublicKey(), amount);
            } else if (wallet1.equalsIgnoreCase("walletB") && wallet2.equalsIgnoreCase("walletA")) {
                walletServiceB.sendFunds(walletServiceA.getPublicKey(), amount);
            } else {
                walletServiceA.sendFunds(walletServiceA.getPublicKey(), amount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/balance/" + wallet1;
    }

    @GetMapping(value = "/alltransactions")
    public String alltransactions(Model model) {
        String text = this.bChain.toJSON();

        String pretty = new JSONObject(text).toString(2);
        model.addAttribute("transactionsText", pretty);
        return "alltransactions";
    }

    @GetMapping(value = "/valid")
    public String testValidity(Model model) {
        //return "Valid : " + this.bChain.isValid();
        model.addAttribute("validityText", this.bChain.isValid());
        return "valid";
    }
}
