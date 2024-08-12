// Create an IOUController class that implements the endpoints below. Ensure your service class is injected as a dependency and apply the appropriate annotations
// Method	Endpoint	Description
// GET	/api/ious	Retrieve a list of (optionally filtered) IOUs
// GET	/api/ious/{id}	Retrieve a specific IOU by its ID
// POST	/api/ious	Create a new IOU
// PUT	/api/ious/{id}	Update an existing IOU by ID
// DELETE	/api/ious/{id}	Delete an IOU by ID

package com.cbfacademy.restapiexercise.ious;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ious") //sets the base path for all endpoints in this controller. Any request URI handled by this class will start with "/api/books". Therefore, each method’s path is relative to this base path. This approach helps avoid repetition and makes the code cleaner and easier to maintain.

public class IOUController {
  private final IOUService iouService;

    @Autowired
    public IOUController(IOUService iouService) { // iouService class injected as a dependency
    this.iouService = iouService;
  }
  
  @GetMapping(produces = "application/json")
  public List<IOU> getAllIOUs() {
    return iouService.getAllIOUs();
  }

  @GetMapping(value = "/{id}", produces = "application/json")
  public IOU getIOU(@PathVariable UUID id) {
      return iouService.getIOU(id);
  }

  @PostMapping(produces = "application/json")
  public IOU createIou(@RequestBody IOU iou) {
    return iouService.createIOU (iou);
  }

  @PutMapping(value = "/{id}", produces = "application/json")
  public IOU updateIOU(@PathVariable UUID id, @RequestBody IOU updatedIOU) {
      return iouService.updateIOU(id, updatedIOU);
  }

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
//   @PostMapping(produces = "application/json")
//   public IOU createIou(@RequestBody IOU iou) {
//     return iouService.createIOU (iou);
//   }
// }






