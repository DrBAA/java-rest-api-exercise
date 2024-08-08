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
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;



@Service
public class IOUService {
  
  private final IOURepository iouRepository; // IOURepository as a dependency

  public IOUService(IOURepository iouRepository) {
    this.iouRepository = iouRepository;
  }

  public List<IOU> getAllIOUs() {
    return this.iouRepository.findAll();
  }

  public IOU getIOU(UUID id) throws NoSuchElementException {
    return this.iouRepository.findById(id).orElseThrow();
  }

  public IOU createIOU(IOU iou) throws IllegalArgumentException, OptimisticLockingFailureException {
    return this.iouRepository.save(iou);
  }

  // public IOU updateIOU(UUID id, IOU updatedIOU) throws NoSuchElementException {
  //   return this.iouRepository;
  // }

  public void deleteIOU(UUID id) {
    this.iouRepository.delete(getIOU(id));
  }


}











/*
@Service
public class IOUService {
    private final IOURepository iouRepository;

    public IOUService(IOURepository iouRepository){ // takes IOURepository as a dependency injection
        this.iouRepository = iouRepository;

    }

    // SERVICE LAYER
    public List<IOU> getAllIOUs(){ // gets IOUs and returns an entity
        return this.iouRepository.findAll();
    }


    public IOU getIOU (UUID id) throws NoSuchElementException {
        return this.iouReposiory.findAll(id).orElseThrow();
    }

}

*/
