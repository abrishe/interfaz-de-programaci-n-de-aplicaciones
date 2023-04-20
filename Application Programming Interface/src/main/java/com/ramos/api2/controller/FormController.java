package com.ramos.api2.controller;

import com.ramos.api2.model.Form;
import com.ramos.api2.repository.FormRepository;
import com.ramos.api2.service.FormService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
// @RequestMapping("/forms")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class FormController {

    private final FormRepository formRepository;

    @GetMapping("/forms/")
    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    @GetMapping("/forms/{id}")
    public ResponseEntity<Form> getFormById(@PathVariable Integer id) {
        return formRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/forms")
    public ResponseEntity<Form> createForm(@RequestBody Form form) {
        Form savedForm = formRepository.save(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedForm);
    }

    @PutMapping("/forms/{id}")
    public ResponseEntity<Form> editForm(@PathVariable Integer id, @RequestBody Form form) {
        return formRepository.findById(id)
                .map(existingForm -> {
                    existingForm.setFormName(form.getFormName());
                    existingForm.setFormLastName(form.getFormLastName());
                    existingForm.setFormSecondLastName(form.getFormSecondLastName());
                    
                    existingForm.setFormEmail(form.getFormEmail());
                    existingForm.setFormNif(form.getFormNif());
                    existingForm.setFormDate(form.getFormDate());
                    
                    existingForm.setFormFamilyNumber(form.isFormFamilyNumber());
                    existingForm.setFormOldMayor(form.isFormOldMayor());
                    existingForm.setFormGender(form.getFormGender());
                    
                    existingForm.setFormColor(form.getFormColor());
                    Form updatedForm = formRepository.save(existingForm);
                    return ResponseEntity.ok(updatedForm);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/forms/{id}")
    public void deleteForm(@PathVariable Integer id) {
    }
    /*
    *
@DeleteMapping("/forms/{id}")
public ResponseEntity<Object> deleteForm(@PathVariable Integer id) {
    Optional<Form> formOptional = formRepository.findById(id);
    if (formOptional.isPresent()) {
        formRepository.delete(formOptional.get());
        return ResponseEntity.noContent().build();
    } else {
        return ResponseEntity.notFound().build();
    }
}
    * */
}
