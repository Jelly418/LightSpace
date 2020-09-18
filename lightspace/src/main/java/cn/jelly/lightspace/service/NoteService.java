package cn.jelly.lightspace.service;

import cn.jelly.lightspace.entity.Note;
import cn.jelly.lightspace.mapper.NoteMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NoteService {
    @Resource
    private NoteMapper noteMapper;

    public Note findById(int id) {
        return noteMapper.findById(id);
    }

    public void addNote(Note question) {
        noteMapper.addNote(question);
    }

    public void updateNote(Note note) {
         noteMapper.updateNote(note);
    }
}
