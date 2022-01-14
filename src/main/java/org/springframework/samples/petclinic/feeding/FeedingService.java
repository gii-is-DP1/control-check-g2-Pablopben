package org.springframework.samples.petclinic.feeding;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeedingService {

    @Autowired
    FeedingRepository feedingRepository;

    @Transactional
    public List<Feeding> getAll(){
        return this.feedingRepository.findAll();
    }

    @Transactional
    public Feeding getByName(String name){
        return null;
    }

    @Transactional
    public List<FeedingType> getAllFeedingTypes(){
        return this.feedingRepository.findAllFeedingTypes();
    }

    @Transactional
    public FeedingType getFeedingType(String typeName) {
        return this.feedingRepository.getTypeByName(typeName);
    }

    @Transactional
    public Feeding save(Feeding p) throws UnfeasibleFeedingException {
        if(p.getFeedingType().getPetType() != p.getPet().getType()){
            throw new UnfeasibleFeedingException();
        }else{
            return this.feedingRepository.save(p);   
        }
            
    }

    
}
