package map;

import com.google.gson.Gson;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * A chunk is a part of the map.
 * @author Giacchini Valerio
 * @version 0.3.3
 * @see Way
 * @since 22/04/2022
 */
public class Chunk {
	protected final Integer id;
	protected final Way start;
	protected final Way end;

	public static final int CHUNK_WIDTH = 256;

	/**
	 * @param id the id of the chunk
	 * @param start the start way of the chunk
	 * @param end the end way of the chunk
	 */
	public Chunk (Integer id, Way start, Way end) {
		this.id = id;
		this.start = start;
		this.end = end;
	}

	public Integer getId() {
		return id;
	}

	public Way getStart() {
		return start;
	}

	public Way getEnd() {
		return end;
	}

	/**
	 * example of a chunk name "<id>_<start><end>" --> 1_lr --> id = 1, start = left, end = right
	 * @return the list of all chunks names
	 */
	public static String @NotNull [] getChunksName () {
		Gson gson = new Gson ();
		File file = new File ("src/main/resources/chunk_names.json");
		try {
			return gson.fromJson (new String (java.nio.file.Files.readAllBytes (file.toPath ())), String[].class);
		} catch (IOException e) {
			throw new RuntimeException (e);
		}
	}

	/**
	 * @return the list of chunks created (from the folder)
	 * @author Giacchini Valerio
	 */
	public static Chunk @NotNull [] getChunks () {
		String[] names = Chunk.getChunksName ();
		List<Chunk> chunks = new ArrayList<> ();

		for (String name : names)
			chunks.add (new Chunk (
					Integer.parseInt (name.substring (0, name.indexOf ("_"))),
					Way.fromString (name.substring (name.indexOf ("_") + 1, name.length () - 1)),
					Way.fromString (name.substring (name.indexOf ("_") + 2))
			));

		return chunks.toArray (new Chunk[0]);
	}

	/**
	 * @param chunk the chunk to check
	 * @return true if this chunk can be connected to the given chunk
	 * @author Giacchini Valerio
	 */
	@Contract (pure = true)
	public boolean isConnectable (Chunk chunk) {
		if (chunk == null) return false;
		return (this.end == chunk.start) && !this.equals (chunk);
	}

	@Override
	public String toString () {
		return "Chunk{" +
				"id=" + id +
				", start=" + this.start +
				", end=" + this.end +
				'}';
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (!(o instanceof Chunk chunk)) return false;
		return this.id.equals (chunk.id) && this.start == chunk.start && this.end == chunk.end;
	}

	@Override
	public int hashCode () {
		return Objects.hash (id, start, end);
	}
}
