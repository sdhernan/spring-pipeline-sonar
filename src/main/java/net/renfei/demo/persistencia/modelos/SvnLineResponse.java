package net.renfei.demo.persistencia.modelos;

import java.util.List;
import java.util.Map;

public class SvnLineResponse {

	private String executionDate;
	private List<FileLineInfo> files;
	private Map<String, Integer> totalByExtension;
	private int totalLines;

	/**
	 * 
	 */
	public SvnLineResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getExecutionDate() {
		return executionDate;
	}

	public List<FileLineInfo> getFiles() {
		return files;
	}

	public Map<String, Integer> getTotalByExtension() {
		return totalByExtension;
	}

	public int getTotalLines() {
		return totalLines;
	}

	public void setExecutionDate(String executionDate) {
		this.executionDate = executionDate;
	}

	public void setFiles(List<FileLineInfo> files) {
		this.files = files;
	}

	public void setTotalByExtension(Map<String, Integer> totalByExtension) {
		this.totalByExtension = totalByExtension;
	}

	public void setTotalLines(int totalLines) {
		this.totalLines = totalLines;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SvnLineResponse [executionDate=");
		builder.append(executionDate);
		builder.append(", totalLines=");
		builder.append(totalLines);
		builder.append("]");
		return builder.toString();
	}

}
