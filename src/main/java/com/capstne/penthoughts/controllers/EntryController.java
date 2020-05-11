package com.capstne.penthoughts.controllers;

import com.capstne.penthoughts.daos.EntryDAO;
import com.capstne.penthoughts.model.Entries;
import com.capstne.penthoughts.model.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/entry")
public class EntryController {

    @Autowired
    EntryDAO entryDAO;

    @GetMapping(path="/", produces = "application/json")
    public Entries getAllEntries()
    {
        return entryDAO.getEntriesList();
    }

    @PostMapping(path="/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Entry> addEntry(@RequestBody Entry entry){
        long id = entryDAO.getEntriesList().getEntryList().size() + 1;
        entry.setId(id);
        entry.setCreatedTime(LocalDateTime.now());
        entry.setUpdatedTime(null);

        entryDAO.addEntry(entry);
        return new ResponseEntity<Entry>(entry, HttpStatus.CREATED);
    }
}
