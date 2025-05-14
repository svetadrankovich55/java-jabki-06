public class Television {

    private int currentChannel;
    private int volume;

    public Television(int currentChannel, int volume) {
        this.currentChannel = currentChannel;
        this.volume = volume;
    }

    public int getCurrentChannel() {
        return currentChannel;
    }

    public void setCurrentChannel(int channel) {
        if (channel < 1 || channel > 50) {
            throw new IllegalArgumentException("Канал должен быть от 1 до 50");
        }
        this.currentChannel = channel;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        if (volume < 0 || volume > 100) {
            throw new IllegalArgumentException("Громкость должна быть от 0 до 100");
        }
        this.volume = volume;
    }

    public void nextChannel() {
        if (currentChannel == 50) {
            currentChannel = 1;
        } else {
            currentChannel++;
        }
    }
}