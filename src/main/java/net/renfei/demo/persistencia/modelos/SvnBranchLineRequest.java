package net.renfei.demo.persistencia.modelos;

/**
 * The Class SvnBranchLineRequest.
 */
public class SvnBranchLineRequest {
	/** The repo URL. */
	private String repoURL;

	/** The user. */
	private String user;

	/** The password. */
	private String password;

	/** The branch. */
	private String branch;

	/**
	 * Instantiates a new svn branch line request.
	 */
	public SvnBranchLineRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the branch.
	 *
	 * @return the branch
	 */
	public String getBranch() {
		return branch;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Gets the repo URL.
	 *
	 * @return the repo URL
	 */
	public String getRepoURL() {
		return repoURL;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Sets the branch.
	 *
	 * @param branch the new branch
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Sets the repo URL.
	 *
	 * @param repoURL the new repo URL
	 */
	public void setRepoURL(String repoURL) {
		this.repoURL = repoURL;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
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
		StringBuilder builder = new StringBuilder();
		builder.append("SvnBranchLineRequest [repoURL=");
		builder.append(repoURL);
		builder.append(", user=");
		builder.append(user);
		builder.append(", password=");
		builder.append(password);
		builder.append(", branch=");
		builder.append(branch);
		builder.append("]");
		return builder.toString();
	}

}
