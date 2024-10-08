package by.vsu.msp.model;

import by.vsu.msp.domain.Note;

import java.util.*;

public class NoteRepository {
	private final static Map<Integer, Note> notes = new HashMap<>();
	static {
		create(note("abc", null, date(15, Calendar.AUGUST, 2025), false));
		create(note("абв", null, date(27, Calendar.AUGUST, 2025), true));
		create(note("xyz", null, date(13, Calendar.SEPTEMBER, 2025), false));
		create(note("где", null, date(29, Calendar.SEPTEMBER, 2025), true));
		create(note("mnk", null, date(4, Calendar.OCTOBER, 2025), false));
	}

	public static void create(Note note) {
		Integer id = notes.keySet().stream().max(Integer::compare).orElse(0) + 1;
		note.setId(id);
		notes.put(id, note);
	}

	public static Optional<Note> read(Integer id) {
		return Optional.ofNullable(notes.get(id));
	}

	public static List<Note> read() {
		return notes.values().stream().sorted(Comparator.comparingInt(Note::getId)).toList();
	}

	public static void update(Note note) {
		if(!notes.containsKey(note.getId())) {
			notes.put(note.getId(), note);
		}
		//notes.computeIfPresent(note.getId(), (key, value) -> note);
	}

	public static void delete(Integer id) {
		notes.remove(id);
	}

	private static Note note(String title, String content, Date date, boolean done) {
		Note note = new Note();
		note.setTitle(title);
		note.setContent(content);
		note.setDate(date);
		note.setDone(done);
		return note;
	}

	private static Date date(int day, int month, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		return calendar.getTime();
	}
}
