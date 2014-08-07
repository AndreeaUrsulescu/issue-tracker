package internship.issuetracker.service;

import internship.issuetracker.entities.Activation;
import internship.issuetracker.repository.ActivationRepository;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivationService {

    private static final Logger LOG = Logger.getLogger(ActivationService.class.getName());

    @Autowired
    private ActivationRepository activationRepository;

    public void addActivation(Activation activation) {
        this.activationRepository.create(activation);
        LOG.log(Level.INFO, "Activation entity was persisted");
    }

    public Activation getActivation(String keyHash) {
        return activationRepository.findActivationByKeyHash(keyHash);
    }

    public void removeActivation(Activation activation) {
        this.activationRepository.remove(activation);
        LOG.log(Level.INFO, "Activation entity was removed");
    }
}
