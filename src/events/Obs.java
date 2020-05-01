package events;

public interface Obs {
	void addListener(Lisnr l);
	void removeListener(Lisnr l);
	void notifySve();
}
