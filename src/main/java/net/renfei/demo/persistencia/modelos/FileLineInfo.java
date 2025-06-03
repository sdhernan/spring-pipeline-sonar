package net.renfei.demo.persistencia.modelos;

/**
 * The Class FileLineInfo.
 */
public class FileLineInfo {

	/** The path. */
	private String path;

	/** The lines. */
	private int lines;

	/**
	 * Instantiates a new file line info.
	 *
	 * @param path  the path
	 * @param lines the lines
	 */
	public FileLineInfo(String path, int lines) {
		this.path = path;
		this.lines = lines;
	}

	/**
	 * Gets the lines.
	 *
	 * @return the lines
	 */
	public int getLines() {
		return lines;
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets the lines.
	 *
	 * @param lines the new lines
	 */
	public void setLines(int lines) {
		this.lines = lines;
	}

	/**
	 * Sets the path.
	 *
	 * @param path the new path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileLineInfo [path=");
		builder.append(path);
		builder.append(", lines=");
		builder.append(lines);
		builder.append("]");
		return builder.toString();
	}

}
