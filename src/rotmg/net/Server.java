package rotmg.net;

public class Server {

	public String name;
	public String address;
	public int port;
	public LatLong latLong;
	public double usage;
	public Boolean isAdminOnly;

	public Server setName(String param1) {
		this.name = param1;
		return this;
	}

	public Server setAddress(String param1) {
		this.address = param1;
		return this;
	}

	public Server setPort(int param1) {
		this.port = param1;
		return this;
	}

	public Server setLatLong(double param1, double param2) {
		this.latLong = new LatLong(param1, param2);
		return this;
	}

	public Server setUsage(double param1) {
		this.usage = param1;
		return this;
	}

	public Server setIsAdminOnly(boolean param1) {
		this.isAdminOnly = param1;
		return this;
	}

	public int priority() {
		if (this.isAdminOnly) {
			return 2;
		}
		if (this.isCrowded()) {
			return 1;
		}
		return 0;
	}

	public boolean isCrowded() {
		return this.usage >= 0.66;
	}

	public boolean isFull() {
		return this.usage >= 1;
	}


	@Override
	public String toString() {
		return "Server{" +
				"name='" + name + '\'' +
				", address='" + address + '\'' +
				", port=" + port +
				", latLong=" + latLong +
				", usage=" + usage +
				", isAdminOnly=" + isAdminOnly +
				'}';
	}
}
