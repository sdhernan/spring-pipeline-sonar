package net.renfei.demo.persistencia.excepciones;

//FileStorageException.java
public class FileStorageException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6709275460201331719L;

	public FileStorageException(String message) {
		super(message);
	}

	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
