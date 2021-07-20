package main.service;

import main.model.DoingEntity;
import main.model.DoingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class DoingService {

    @Autowired
    private DoingRepository doingRepository;

    public Collection<DoingEntity> list() {
        Iterable<DoingEntity> iterable = doingRepository.findAll();
        ArrayList<DoingEntity> doingEntities = new ArrayList<>();
        iterable.forEach(doingEntities::add);
        return doingEntities;
    }

    public DoingEntity getDoing(int id) {
        Optional<DoingEntity> searchResult = doingRepository.findById(id);
        if (searchResult.isPresent()) {
            return searchResult.get();
        }
        return null;
    }

    public int addDoing(DoingEntity doingEntity) {
        DoingEntity savedDoingEntity = doingRepository.save(doingEntity);
        return savedDoingEntity.getId();
    }

    public boolean putDoing(int id, DoingEntity doingEntity) {
        if (doingRepository.existsById(id)) {
            doingRepository.save(doingEntity);
            return true;
        }
        return false;
    }

    public void clear() {
        doingRepository.deleteAll();
    }

    public boolean deleteDoing(int id) {
        if (doingRepository.existsById(id)) {
            doingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
