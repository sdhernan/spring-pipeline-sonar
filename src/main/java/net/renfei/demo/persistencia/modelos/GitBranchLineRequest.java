package net.renfei.demo.persistencia.modelos;

/**
 * Modelo de petición para el conteo de líneas de código en un branch de Git.
 * Compatible con GitHub, GitLab y Bitbucket.
 */
public class GitBranchLineRequest {

	/** URL del repositorio Git. */
	private String repoGit;

	/** Usuario para autenticación. */
	private String user;

	/** Token de acceso o contraseña. */
	private String token;

	/**
	 * Nombre del branch (opcional, si no se especifica usa el branch por defecto).
	 */
	private String branch;

	/**
	 * Constructor vacío requerido para deserialización.
	 */
	public GitBranchLineRequest() {
	}

	/**
	 * Constructor con todos los parámetros.
	 *
	 * @param repoGit URL del repositorio Git
	 * @param user    Nombre de usuario
	 * @param token   Token de acceso (o contraseña)
	 * @param branch  Nombre del branch
	 */
	public GitBranchLineRequest(String repoGit, String user, String token, String branch) {
		this.repoGit = repoGit;
		this.user = user;
		this.token = token;
		this.branch = branch;
	}

	/**
	 * Gets the branch.
	 *
	 * @return el nombre del branch
	 */
	public String getBranch() {
		return branch;
	}

	/**
	 * Gets the repo git.
	 *
	 * @return la URL del repositorio Git
	 */
	public String getRepoGit() {
		return repoGit;
	}

	/**
	 * Gets the token.
	 *
	 * @return el token de acceso o contraseña
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Gets the user.
	 *
	 * @return el usuario
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Sets the branch.
	 *
	 * @param branch el nombre del branch a establecer
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}

	/**
	 * Sets the repo git.
	 *
	 * @param repoGit la URL del repositorio Git a establecer
	 */
	public void setRepoGit(String repoGit) {
		this.repoGit = repoGit;
	}

	/**
	 * Sets the token.
	 *
	 * @param token el token de acceso o contraseña a establecer
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Sets the user.
	 *
	 * @param user el usuario a establecer
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GitBranchLineRequest [repoGit=" + repoGit + ", user=" + user + ", branch=" + branch + "]";
	}
}