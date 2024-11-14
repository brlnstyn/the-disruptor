package com.desacode.disruptor.transaction;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final TransactionServiceNoDisruptor transactionServiceNoDisruptor;
    private final TransactionTestService transactionTestService;

    @PostMapping("/add")
    public String add(
            @RequestBody Transaction transaction
    ){
        try {
            transactionService.processTransaction(transaction);
            return "Success add data";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @PostMapping("/addNoDisruptor")
    public String addNoDisruptor(
            @RequestBody Transaction transaction
    ){
        try {
            transactionServiceNoDisruptor.processTransaction(transaction);
            return "Success add data";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @GetMapping("/withDisruptor/{number}")
    public void withDisruptor(
        @PathVariable int number
    ) throws Exception {
        transactionTestService.benchmarkWithDisruptor(number);
    }

    @GetMapping("/withoutDisruptor/{number}")
    public void withoutDisruptor(
            @PathVariable int number
    ) throws Exception {
        transactionTestService.benchmarkNoDisruptor(number);
    }
}
