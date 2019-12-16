
    import org.opencv.core.*;
    import org.opencv.dnn.Net;
import org.opencv.dnn.Dnn;
    import org.opencv.imgcodecs.Imgcodecs;
    import org.opencv.imgproc.Imgproc;
    import org.opencv.utils.Converters;

    import java.awt.image.BufferedImage;
    import java.io.FileNotFoundException;
    import org.opencv.highgui.HighGui;
    import java.io.IOException;
    import java.io.PrintWriter;
    import java.io.UnsupportedEncodingException;
    import java.util.*;


    import org.opencv.core.Core;
    import org.opencv.videoio.VideoCapture;

    import java.util.ArrayList;
import java.util.List;


    public class YOLO {
       //Our class that Actually Implements the algorithm
        public static class YOLOO {
            //YOLO can predict 80 classes, however for our case we only use 1 class. We filter this alter
            public  List<String> getOutputNames(Net net) {
                List<String> names = new ArrayList<>();

                List<Integer> outLayers = net.getUnconnectedOutLayers().toList();
                List<String> layersNames = net.getLayerNames();

                outLayers.forEach((item) -> names.add(layersNames.get(item - 1)));
                return names;
            }
            //This is an overloaded version of the function. We can ignore this for now and move onto the other getBoxes() function

            public LinkedList<Point> getBoxes(String imagename) {
                System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

                String modelWeights = "C:\\Users\\Rahul\\IdeaProjects\\Yolo\\src\\yolov3-tiny.weights";
                String modelConfiguration = "C:\\Users\\Rahul\\IdeaProjects\\Yolo\\src\\yolov3-tiny.cfg";

                Net net = Dnn.readNetFromDarknet(modelConfiguration, modelWeights);
                System.out.println("we reached here");
                Mat image = Imgcodecs.imread(imagename);
                System.out.println(image.size());
                Size sz = new Size(416, 416);
                Mat blob = Dnn.blobFromImage(image, 0.00392, sz, new Scalar(0), true, false);
                net.setInput(blob);

                List<Mat> result = new ArrayList<>();
                List<String> outBlobNames = getOutputNames(net);

                net.forward(result, outBlobNames);

                outBlobNames.forEach(System.out::println);
                result.forEach(System.out::println);

                float confThreshold = 0.6f;
                List<Integer> clsIds = new ArrayList<>();
                List<Float> confs = new ArrayList<>();
                List<Boolean>isShow = new ArrayList<>();
                List<Rect> rects = new ArrayList<>();
                LinkedList<Point> coords = new LinkedList<>();
                for (int i = 0; i < result.size(); ++i)
                {
                    // each row is a candidate detection, the 1st 4 numbers are
                    // [center_x, center_y, width, height], followed by (N-4) class probabilities
                    Mat level = result.get(i);
                    for (int j = 0; j < level.rows(); ++j)
                    {
                        Mat row = level.row(j);
                        Mat scores = row.colRange(5, level.cols());
                        Core.MinMaxLocResult mm = Core.minMaxLoc(scores);
                        float confidence = (float)mm.maxVal;
                        Point classIdPoint = mm.maxLoc;
                        if (confidence > confThreshold)
                        {
                            int centerX = (int)(row.get(0,0)[0] * image.cols());
                            int centerY = (int)(row.get(0,1)[0] * image.rows());
                            int width   = (int)(row.get(0,2)[0] * image.cols());
                            int height  = (int)(row.get(0,3)[0] * image.rows());
                            int left    = centerX - width  / 2;
                            int top     = centerY - height / 2;

                            clsIds.add((int)classIdPoint.x);
                            if(classIdPoint.x==0.0)
                                isShow.add(true);
                            else
                                isShow.add(false);
                            confs.add((float)confidence);
                            rects.add(new Rect(left, top, width, height));
                        }
                    }
                }

                // Apply non-maximum suppression procedure.
                float nmsThresh = 0.5f;
                MatOfFloat confidences = new MatOfFloat(Converters.vector_float_to_Mat(confs));
                Rect[] boxesArray = rects.toArray(new Rect[0]);
                MatOfRect boxes = new MatOfRect(boxesArray);
                MatOfInt indices = new MatOfInt();
                Dnn.NMSBoxes(boxes, confidences, confThreshold, nmsThresh, indices);

                // Draw result boxes:
                int [] ind = indices.toArray();
                for (int i = 0; i < ind.length; ++i)
                {
                    int idx = ind[i];
                    Rect box = boxesArray[idx];
                    if(isShow.get(i)) {
                        Imgproc.rectangle(image, box.tl(), box.br(), new Scalar(0, 0, 255), 2);
                        coords.add(box.tl());
                        coords.add(box.br());
                        System.out.println(box.tl());
                        System.out.println(box.br());
                    }
                }
                Imgcodecs.imwrite("out.png", image);
                return coords;
            }
            
            
            //Now we are at the function that impelments modularity for ease of developement.
            String modelWeights = "yolov3.weights";  // Loading the weights and the config file.
            String modelConfiguration = "yolov3.cfg";

            //The config file specifies the structure of the network while the weights contain the actual matrix values.
            Net net = Dnn.readNetFromDarknet(modelConfiguration, modelWeights); //loading the model
            //here the Mat aa corresponds to the camera frame and String room corresponds to the room
            public LinkedList<Point> getBoxes(Mat aaa , String room) {

                System.loadLibrary(Core.NATIVE_LIBRARY_NAME);


                System.out.println("we reached here");
                Mat image = aaa;
                System.out.println(image.size());
                Size sz = new Size(416, 416);
                //Getting the output vector from yolo
                Mat blob = Dnn.blobFromImage(image, 0.00392, sz, new Scalar(0), true, false);
                net.setInput(blob);
//results
                List<Mat> result = new ArrayList<>();
                List<String> outBlobNames = getOutputNames(net);
                //obtaining the results
                net.forward(result, outBlobNames);

                outBlobNames.forEach(System.out::println);
                result.forEach(System.out::println);
               //Setting a high confidence threshold on a scale of 0 to 1
                float confThreshold = 0.7f;
                List<Integer> clsIds = new ArrayList<>();  //Each class detected has an ID
                List<Float> confs = new ArrayList<>(); //Each object detected has a confidence score associated to it
                List<Boolean>isShow = new ArrayList<>(); //Show only if its a person
                List<Rect> rects = new ArrayList<>(); //Coordinates of rectangle of object x y
                LinkedList<Point> coords = new LinkedList<>();//Coordinates of rectangle w h
                
                for (int i = 0; i < result.size(); ++i)
                {
                    // each row is a candidate detection, the 1st 4 numbers are
                    // [center_x, center_y, width, height], followed by (N-4) class probabilities
                    Mat level = result.get(i);
                    for (int j = 0; j < level.rows(); ++j)
                    {
                        Mat row = level.row(j);
                        Mat scores = row.colRange(5, level.cols());
                        Core.MinMaxLocResult mm = Core.minMaxLoc(scores);
                        float confidence = (float)mm.maxVal;
                        Point classIdPoint = mm.maxLoc;
                        if (confidence > confThreshold) //Checking if the required confidence is in the prediction
                        {  //Obtain the bounding boxes
                            int centerX = (int)(row.get(0,0)[0] * image.cols());
                            int centerY = (int)(row.get(0,1)[0] * image.rows());
                            int width   = (int)(row.get(0,2)[0] * image.cols());
                            int height  = (int)(row.get(0,3)[0] * image.rows());
                            int left    = centerX - width  / 2;
                            int top     = centerY - height / 2;

                            clsIds.add((int)classIdPoint.x);
                            if(classIdPoint.x==0.0) //if the class is a person
                                isShow.add(true);
                            else
                                isShow.add(false);
                            confs.add((float)confidence);
                            rects.add(new Rect(left, top, width, height));
                        }
                    }
                }
                int counter = 0;
                if(isShow.size()!=0) {

                    // Apply non-maximum suppression procedure. This is done to reduce flase positives
                    float nmsThresh = 0.5f;

                    MatOfFloat confidences = new MatOfFloat(Converters.vector_float_to_Mat(confs));
                    Rect[] boxesArray = rects.toArray(new Rect[0]);
                    MatOfRect boxes = new MatOfRect(boxesArray);
                    MatOfInt indices = new MatOfInt();
                    Dnn.NMSBoxes(boxes, confidences, confThreshold, nmsThresh, indices);

                    // Draw result boxes:
                    int[] ind = indices.toArray();
                    for (int i = 0; i < ind.length; ++i) {
                        int idx = ind[i];
                        Rect box = boxesArray[idx];
                        if (isShow.get(i)) {//Displaying only those indices for which isShow outputs a True value
                            counter++;
                            Imgproc.rectangle(image, box.tl(), box.br(), new Scalar(0, 0, 255), 2); //Dispalying rectangle on Screen
                            coords.add(box.tl());
                            coords.add(box.br());
                            System.out.println(box.tl());
                            System.out.println(box.br());
                        }
                    }
                }

                PrintWriter writer = null;
                try {
                    writer = new PrintWriter("roomcount.txt", "UTF-8"); //Writing to a file the room number and room count.
                    //Our python code will read this file and use these data to upload to firebase
                } catch (FileNotFoundException e) {
                    System.out.println("sup");
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    System.out.println("supid");
                    e.printStackTrace();
                }

                writer.println(room);//room name

                writer.println(Integer.toString(counter)); //room count
                System.out.println(counter);
                writer.close();
                Imgcodecs.imwrite(room+".png", image); //saving detected image for debugging
                String command = "firebase.py";
                try {
                    Process p = Runtime.getRuntime().exec("python "+command ); //Calling the Python command to upload to firebase
                } catch (IOException e) {
                    System.out.println("iuafhsfhisdfsdsf");
                    e.printStackTrace();
                }
                return coords;
            }
        }

        public static void main(String [] args){
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            System.out.println("java.library.path : " + System.getProperty("java.library.path"));
            System.out.println(Core.NATIVE_LIBRARY_NAME);
            Scanner in = new Scanner(System.in);
            String room = in.nextLine();
            VideoCapture camera = new VideoCapture(0); //Initializing the camera

            Mat frame = new Mat();



            if(!camera.isOpened()){
                System.out.println("Error");
            }

            else {
                int coutner = 0;
                while(true){
                    //If camera is opened and we correctly obtain a frame then we call the function
                    if (camera.read(frame)){
                        //anonymous initialization
                        LinkedList<Point>coords = new YOLOO().getBoxes(frame, room);

                        System.out.println(coords);
                        coutner++;
                        //Displaying in a window for debugging
       HighGui.imshow("sdlsdf",frame);
       HighGui.waitKey(3);

                    }

                }
            }
HighGui.destroyAllWindows();
            camera.release();

        }

/*
        public static void main(String [] args){
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            System.out.println("java.library.path : " + System.getProperty("java.library.path"));
            System.out.println(Core.NATIVE_LIBRARY_NAME);
            Scanner in = new Scanner(System.in);
            String room = in.nextLine();
            VideoCapture camera = new VideoCapture(0);





            if(!camera.isOpened()){
                System.out.println("Error");
            }

            else {
                int coutner = 0;

                while(true){
                    Mat frame = Imgcodecs.imread("finalll.png");

                        LinkedList<Point>coords = new YOLOO().getBoxes(frame, room);
                    HighGui.imshow("sdlsdf",frame);
                    HighGui.waitKey(3);
                        System.out.println(coords);


                        System.out.println("Except");


                        coutner++;





                }
            }
            HighGui.destroyAllWindows();
            camera.release();

        }
*/
    }


