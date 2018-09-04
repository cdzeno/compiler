package lt.compiler;

public class Descriptor {
	private String identifier;
	private int address;
	private int length = 1;

	public Descriptor(String id, int addr) {
		identifier = id;
		address = addr;
	}

	public Descriptor(String id) {
		this(id, 0);
	}

	public String getIdentifier() {
		return identifier;
	}

	public int getAddress() {
		return address;
	}

	public int getLength() {
		return length;
	}

	public int assignAddress(int addr) {
		address = addr;
		return address + length;
	}

	public boolean equals(Descriptor d) {
		return this.identifier.equals(d.identifier);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Descriptor)
			return equals((Descriptor) o);
		return false;
	}

	@Override
	public String toString() {
		return identifier + "@" + address + "~" + length;
	}
}
