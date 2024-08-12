package com.cbfacademy.restapiexercise.ious;

import java.util.UUID;
import org.springframework.data.repository.ListCrudRepository;

public interface IOURepository extends ListCrudRepository<IOU, UUID>{
    public void findByBorrower(String borrower){
    
    }
  
}


// Create a new API endpoint to return IOUs for a specific borrower:
// Create a method in your repository interface called findByBorrower that accepts a string borrower parameter.
// Create a method in your service class called getIOUsByBorrower.
// Extend the getIOUS method of your controller to accept an optional query string parameter, e.g.: getIOUs(@RequestParam(required = false) String borrower)
// Check the value of the borrower parameter to determine whether to call the existing service method or the new, filtered, one
// Test the modified endpoint
// Commit your changes