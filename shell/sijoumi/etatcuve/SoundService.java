package shell.sijoumi.etatcuve;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundService implements LineListener {
  private Clip clip;

  public void update(LineEvent event) {}

  public synchronized void sound(String path) {
    play(path, false);
  }

  public synchronized void soundLoop(String path) {
    play(path, true);
  }

  public synchronized boolean isPlaying() {
    return this.clip != null && this.clip.isRunning();
  }

  public synchronized void stop() {
    if (this.clip != null) {
      this.clip.stop();
      this.clip.flush();
      this.clip.close();
      this.clip = null;
    }
  }

  private void play(String path, boolean loop) {
    if (path == null || path.trim().length() == 0) {
      return;
    }
    stop();
    if (path.startsWith("resource:")) {
      playFromResource(path.substring("resource:".length()), loop);
      return;
    }
    playFromFile(path, loop);
  }

  private void playFromFile(String path, boolean loop) {
    try (AudioInputStream ais = AudioSystem.getAudioInputStream(new File(path))) {
      openAndStart(ais, loop);
    } catch (UnsupportedAudioFileException ex) {
      System.out.println("The specified audio file is not supported.");
      ex.printStackTrace();
    } catch (LineUnavailableException ex) {
      System.out.println("Audio line for playing back is unavailable.");
      ex.printStackTrace();
    } catch (IOException ex) {
      System.out.println("Error playing the audio file.");
      ex.printStackTrace();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void playFromResource(String resourcePath, boolean loop) {
    String rp = resourcePath == null ? "" : resourcePath.trim();
    if (!rp.startsWith("/")) {
      rp = "/" + rp;
    }

    InputStream raw = getClass().getResourceAsStream(rp);
    if (raw == null) {
      System.out.println("Audio resource not found: " + rp);
      return;
    }

    try (InputStream in = new BufferedInputStream(raw);
         AudioInputStream ais = AudioSystem.getAudioInputStream(in)) {
      openAndStart(ais, loop);
    } catch (UnsupportedAudioFileException ex) {
      System.out.println("The specified audio resource is not supported.");
      ex.printStackTrace();
    } catch (LineUnavailableException ex) {
      System.out.println("Audio line for playing back is unavailable.");
      ex.printStackTrace();
    } catch (IOException ex) {
      System.out.println("Error playing the audio resource.");
      ex.printStackTrace();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void openAndStart(AudioInputStream ais, boolean loop) throws LineUnavailableException, IOException {
    Line.Info linfo = new Line.Info(Clip.class);
    Line line = AudioSystem.getLine(linfo);
    this.clip = (Clip)line;
    this.clip.addLineListener(this);
    this.clip.open(ais);
    if (loop) {
      this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    } else {
      this.clip.start();
    }
  }
}
