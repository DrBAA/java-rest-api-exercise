// 1. Create an IOUService class that accepts an IOURepository as a dependency and implements the following methods:
// List<IOU> getAllIOUs()
// IOU getIOU(UUID id) throws NoSuchElementException
// IOU createIOU(IOU iou) throws IllegalArgumentException, OptimisticLockingFailureException
// IOU updateIOU(UUID id, IOU updatedIOU) throws NoSuchElementException
// void deleteIOU(UUID id)

package com.cbfacademy.restapiexercise.ious;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;


@Service //- The @Service annotation marks the IOUService class as a service bean.

public class IOUService {
  
  @Autowired
  private final IOURepository iouRepository; // IOURepository as a dependency. The IOUService class is injected with an instance of IOURepository to interact with the database

// IOU service class accepts iouRepository as a dependency through the constructor (constructor injection)
  public IOUService(IOURepository iouRepository) {  
    this.iouRepository = iouRepository;
  }

  // a. List<IOU> getAllIOUs()  
  public List<IOU> getAllIOUs() {
    return this.iouRepository.findAll();
  }

  // copilot - added 12 8 2024.
  // amended 24 8 2024 - add the if statement to validate input (ensure valid UUID format)
  public IOU getIOU(UUID id) throws NoSuchElementException {
    // validate user input to ensure valid UUID format
    if (id == null) {
      throw new IllegalArgumentException ("Invalid UUID provided");
    }
    // Sanitize input (no need for or sanitization with UUIDs because
    // UUIDs don’t require sanitization because they are generated and don’t contain user input)
    return iouRepository.findById(id).orElseThrow(() -> new NoSuchElementException("IOU not found"));
  }

// from copilot - added 12/8/2024 - 
  public IOU createIOU(IOU iou) throws IllegalArgumentException, OptimisticLockingFailureException {
    if (iou == null) {
        throw new IllegalArgumentException("IOU cannot be null");
    }
    return iouRepository.save(iou);
  }

  // d. IOU updateIOU(UUID id, IOU updatedIOU) throws NoSuchElementException
  public IOU updateIOU(UUID id, IOU updatedIOU) throws NoSuchElementException {
//    IOU existingIOU = iouRepository.findById(id).orElseThrow(() -> new NoSuchElementException("IOU not found"));
    IOU existingIOU = getIOU(id);
    existingIOU.setLender(updatedIOU.getLender());      
    existingIOU.setAmount(updatedIOU.getAmount());
    existingIOU.setBorrower(updatedIOU.getBorrower());
    existingIOU.setDateTime(updatedIOU.getDateTime());
    return iouRepository.save(existingIOU);
  }

    // FROM tutor Andrew
  public void deleteIOU(UUID id) {
    this.iouRepository.delete(getIOU(id));
  }


// added 12/8/2024 
  public List<IOU> getIOUsByBorrower(String borrower) {
    return iouRepository.findByBorrower(borrower);
  }

}

// --===============================


/*
@Service //- The @Service annotation marks the IOUService class as a service bean.

public class IOUService {
  
  @Autowired
  private final IOURepository iouRepository; // IOURepository as a dependency. The IOUService class is injected with an instance of IOURepository to interact with the database

// IOU service class accepts iouRepository as a dependency through the constructor (constructor injection)
  public IOUService(IOURepository iouRepository) {  
    this.iouRepository = iouRepository;
  }

  // a. List<IOU> getAllIOUs()  
  public List<IOU> getAllIOUs() {
    return this.iouRepository.findAll();
  }

  // tutor's code
  // // b. IOU getIOU(UUID id) throws NoSuchElementException
  public IOU getIOU(UUID id) throws NoSuchElementException {
    return this.iouRepository.findById(id).orElseThrow();
  }

  // copilot - added 12 8 2024. This version explicitly throws a NoSuchElementException with a custom message “IOU not found” if the IOU is not found in the repository.
//   public IOU getIOU(UUID id) throws NoSuchElementException {
//     return iouRepository.findById(id).orElseThrow(() -> new NoSuchElementException("IOU not found"));
// }

// from tutor - This version does not include a null check for the iou parameter. If iou is null, the method will pass the null value to the save method of the iouRepository. Depending on the implementation of the iouRepository, this could result in a NullPointerException or other unexpected behavior.
  // c. IOU createIOU(IOU iou) throws IllegalArgumentException, OptimisticLockingFailureException
  public IOU createIOU(IOU iou) throws IllegalArgumentException, OptimisticLockingFailureException {
    return this.iouRepository.save(iou);
  }

// from copilot - added 12/8/2024 - this method explicitly checks if the iou parameter is null. If iou is null, it throws an IllegalArgumentException with the message “IOU cannot be null”. This ensures that the method does not attempt to save a null object, which could lead to unexpected behavior or errors in the repository layer.
// public IOU createIOU(IOU iou) throws IllegalArgumentException, OptimisticLockingFailureException {
//   if (iou == null) {
//       throw new IllegalArgumentException("IOU cannot be null");
//   }
//   return iouRepository.save(iou);
// }


  // // d. IOU updateIOU(UUID id, IOU updatedIOU) throws NoSuchElementException
  // public IOU updateIOU(UUID id, IOU updatedIOU) throws NoSuchElementException {
  //   return this.iouRepository.save(iou);
  // }

    // d. IOU updateIOU(UUID id, IOU updatedIOU) throws NoSuchElementException
    public IOU updateIOU(UUID id, IOU updatedIOU) throws NoSuchElementException {
      IOU existingIOU = iouRepository.findById(id).orElseThrow(() -> new NoSuchElementException("IOU not found"));
      existingIOU.setLender(updatedIOU.getLender());      
      existingIOU.setAmount(updatedIOU.getAmount());
      existingIOU.setBorrower(updatedIOU.getBorrower());
      existingIOU.setDateTime(updatedIOU.getDateTime());
      return iouRepository.save(existingIOU);
  }

    // FROM tutor Andrew
    // explanation from Copilot: This method first calls getIOU(id) to fetch the IOU entity.
// It then calls delete(IOU iou) on the iouRepository with the fetched entity.
// This approach ensures that the entity exists before attempting to delete it, which can be useful if you need to perform any additional checks or operations on the entity before deletion. However it does not prompt you to check or to delete
// If the IOU with the given UUID does not exist, getIOU(id) will throw a NoSuchElementException.
  public void deleteIOU(UUID id) {
    this.iouRepository.delete(getIOU(id));
  }

  // FROM COPILOT - added 12 8 2024
  // This method directly calls deleteById(id) on the iouRepository.
// It assumes that the repository can handle the deletion by ID without needing to fetch the entity first.
// This is typically more efficient as it avoids an additional database query to fetch the entity.
  // public void deleteIOU(UUID id) {
  //   this.iouRepository.deleteById(id);
  // }  

// }

// added 12/8/2024 
  public List<IOU> getIOUsByBorrower(String borrower) {
    return iouRepository.findByBorrower(borrower);
  }

}
*/
