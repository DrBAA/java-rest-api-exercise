// Create an IOUController class that implements the endpoints below.
// Ensure your service class is injected as a dependency and apply the appropriate annotations
// Method	Endpoint	Description
// GET	/api/ious	Retrieve a list of (optionally filtered) IOUs
// GET	/api/ious/{id}	Retrieve a specific IOU by its ID
// POST	/api/ious	Create a new IOU
// PUT	/api/ious/{id}	Update an existing IOU by ID
// DELETE	/api/ious/{id}	Delete an IOU by ID

package com.cbfacademy.restapiexercise.ious;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ious") //sets the base path for all endpoints in this controller. Any request URI handled by this class will start with "/api/books". Therefore, each method’s path is relative to this base path. This approach helps avoid repetition and makes the code cleaner and easier to maintain.

public class IOUController {

  // iouService class injected as a dependency
  @Autowired  
  private final IOUService iouService;
 
  public IOUController(IOUService iouService) { 
  this.iouService = iouService;
  }
  
  // amended 12 8 2024 - 
  // Extend the getIOUS method of your controller to accept an optional query string parameter, e.g.: getIOUs(@RequestParam(required = false) String borrower)
  // Retrieve a list of (optionally filtered) IOUs
  @GetMapping(produces = "application/json")
  public List<IOU> getALLIOUs(@RequestParam(required = false) String borrower) {
      if (borrower != null) {
          // Call the filtered service method based on the borrower value
          return iouService.getIOUsByBorrower(borrower);
      } else {
          // Call the existing service method (getAllIOUs)
          return iouService.getAllIOUs();
      }
  }

  // Retrieve a specific IOU by its ID
  @GetMapping(value = "/{id}", produces = "application/json")
  public IOU getIOU(@PathVariable UUID id) {
      return iouService.getIOU(id);
  }

  // Create a new IOU - my original code

  // @PostMapping(produces = "application/json")
  // public IOU createIou(@RequestBody IOU iou) {
  //   return iouService.createIOU(iou);
  // }

  // Create a new IOU - amendments after speaking to Kelly - group member CBF
  // added  @ResponseStatus(HttpStatus.CREATED)
  @ResponseStatus(HttpStatus.CREATED) // sets the HTTP status code to 201 CREATED for the response regardless of the return type.It eliminates the need to use ResponseEntity to set the status code manually.
  @PostMapping(produces = "application/json")
  public IOU createIou(@RequestBody IOU iou) {
    return iouService.createIOU(iou);
  }

  
//   // Create a new IOU - from Kelly - group member CBF - was not working as createdIOU cannot be resolved to a variable. added the createdIou variable in order to call the iouService.createIOU(iou) method to create the IOU object and then return it in the ResponseEntity.
  @PostMapping(produces = "application/json")
  public ResponseEntity<IOU> createIOU(@RequestBody IOU iou) {
    IOU createdIou = iouService.createIOU(iou);
      return new ResponseEntity<>(createdIou, HttpStatus.CREATED);
  }  

// //   // copilot
// @PostMapping(produces = "application/json")
// public ResponseEntity<IOU> createIou(@RequestBody IOU iou) {
//     IOU createdIou = iouService.createIOU(iou);
//     return ResponseEntity.status(HttpStatus.CREATED).body(createdIou);
// }


  // Update an existing IOU by ID
  @PutMapping(value = "/{id}", produces = "application/json")
  public IOU updateIOU(@PathVariable UUID id, @RequestBody IOU updatedIOU) {
      return iouService.updateIOU(id, updatedIOU);
  }

  // Delete an IOU by ID
  @DeleteMapping(value = "/{id}", produces = "application/json")
  public void deleteIOU(@PathVariable UUID id) {
      iouService.deleteIOU(id);
  } 

}

// package com.cbfacademy.restapiexercise.ious;
// import java.util.List;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/api/ious")

// public class IOUController {
//   private IOUService iouService;
//   public IOUController(IOUService iouService) {
//     this.iouService = iouService;
//   }
  
//   @GetMapping(produces = "application/json")
//   public List<IOU> getAllIOUs() {
//     return iouService.getAllIOUs();
//   }

  // Original code before below changes
  // @GetMapping(produces = "application/json")
  // public List<IOU> getAllIOUs() {
  //   return iouService.getAllIOUs();
  // }
//   @PostMapping(produces = "application/json")
//   public IOU createIou(@RequestBody IOU iou) {
//     return iouService.createIOU (iou);
//   }
// }






