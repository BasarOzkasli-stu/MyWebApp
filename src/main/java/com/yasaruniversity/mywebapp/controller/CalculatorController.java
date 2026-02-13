package com.yasaruniversity.mywebapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/calc")
@Validated
@Tag(name = "Calculator API", description = "Simple arithmetic operations")
public class CalculatorController {

    @Operation(summary = "Add two numbers")
    @ApiResponse(responseCode = "200", description = "Sum computed",
            content = @Content(schema = @Schema(implementation = Double.class)))
    @GetMapping("/add")
    public ResponseEntity<Double> add(
            @Parameter(description = "First operand") @RequestParam double a,
            @Parameter(description = "Second operand") @RequestParam double b) {
        return ResponseEntity.ok(a + b);
    }

    @Operation(summary = "Subtract two numbers")
    @ApiResponse(responseCode = "200", description = "Difference computed",
            content = @Content(schema = @Schema(implementation = Double.class)))
    @GetMapping("/subtract")
    public ResponseEntity<Double> subtract(
            @Parameter(description = "First operand") @RequestParam double a,
            @Parameter(description = "Second operand") @RequestParam double b) {
        return ResponseEntity.ok(a - b);
    }

    @Operation(summary = "Multiply two numbers")
    @ApiResponse(responseCode = "200", description = "Product computed",
            content = @Content(schema = @Schema(implementation = Double.class)))
    @GetMapping("/multiply")
    public ResponseEntity<Double> multiply(
            @Parameter(description = "First operand") @RequestParam double a,
            @Parameter(description = "Second operand") @RequestParam double b) {
        return ResponseEntity.ok(a * b);
    }

    @Operation(summary = "Divide two numbers")
    @ApiResponse(responseCode = "200", description = "Quotient computed",
            content = @Content(schema = @Schema(implementation = Double.class)))
    @ApiResponse(responseCode = "400", description = "Bad request: division by zero")
    @GetMapping("/divide")
    public ResponseEntity<?> divide(
            @Parameter(description = "Dividend") @RequestParam double a,
            @Parameter(description = "Divisor") @RequestParam double b) {
        if (b == 0.0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Division by zero is not allowed");
        }
        return ResponseEntity.ok(a / b);
    }
}
