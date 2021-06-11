package video.player;

import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

/**
 *
 * @author deepali
 */
public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }
  
  @Override
  public void start(Stage primaryStage) {
      
      JFileChooser fileChooser = new JFileChooser();
   
         	// show open file dialog
         	int result = fileChooser.showOpenDialog( null );
   
         	if ( result == JFileChooser.APPROVE_OPTION ) // user chose a file	
      try{
          System.out.println(fileChooser.getSelectedFile().getAbsolutePath());
          System.out.println(fileChooser.getSelectedFile().getCanonicalPath());
          String workingDir = System.getProperty(fileChooser.getSelectedFile().getAbsolutePath());
          final File f = new File(workingDir, fileChooser.getSelectedFile().getCanonicalPath());

    
    final Media m = new Media(f.toURI().toString());
    final MediaPlayer mp = new MediaPlayer(m);
    final MediaView mv = new MediaView(mp);
    
    final DoubleProperty width = mv.fitWidthProperty(); 
    final DoubleProperty height = mv.fitHeightProperty();
    
    width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
    height.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));
    
    mv.setPreserveRatio(true);
    
    StackPane root = new StackPane(); //stack overflow
    root.getChildren().add(mv);
    
    final Scene scene = new Scene(root, 1366,720 );
    scene.setFill(Color.BLACK);
    
    primaryStage.setScene(scene);
    primaryStage.setTitle("Full Screen Video Player");
    primaryStage.setFullScreen(true);
    primaryStage.show();
    
    mp.play();
    }
      catch(Exception e){
          System.err.println(e.toString());}
  }
}