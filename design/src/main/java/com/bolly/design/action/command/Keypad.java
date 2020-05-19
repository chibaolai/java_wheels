package com.bolly.design.action.command;

public class Keypad {
    private Command playCommand;

    private Command rewindCommand;

    private Command stopCommand;

    public void setPlayCommand(Command playCommand) {
        this.playCommand = playCommand;
    }

    public void setRewindCommand(Command rewindCommand) {
        this.rewindCommand = rewindCommand;
    }

    public void setStopCommand(Command stopCommand) {
        this.stopCommand = stopCommand;
    }

    public void play() {
        playCommand.execute();
    }

    public void rewind() {
        rewindCommand.execute();
    }

    public void stop() {
        stopCommand.execute();
    }

    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        Command play = new PlayCommand(audioPlayer);
        Command stop = new StopCommand(audioPlayer);

        Keypad keypad = new Keypad();
        keypad.setPlayCommand(play);
        keypad.setStopCommand(stop);

        keypad.stop();
    }
}
