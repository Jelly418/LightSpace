package cn.jelly.lightspace.controller;

import cn.jelly.lightspace.entity.Note;
import cn.jelly.lightspace.entity.User;
import cn.jelly.lightspace.service.NoteService;
import cn.jelly.lightspace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class NoteController {
    @Autowired
    NoteService noteService;

    @Autowired
    UserService userService;

    @PostMapping(value = "api/findNoteById")
    public Note findNoteById(@RequestParam(value="id") int id){
        return noteService.findById(id);
    }

    @PostMapping(value = "api/addNote")
    public void addNote(@RequestBody Note note, @RequestParam(value="username") String username){
        User u = userService.findByName(username);
        note.setUid(u.getId());
        noteService.addNote(note);
    }

    @PostMapping(value = "api/updateNote")
    public void updateNote(@RequestBody Note note){
        noteService.updateNote(note);
    }


}
