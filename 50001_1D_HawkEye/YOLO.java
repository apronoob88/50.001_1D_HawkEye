
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

        public static class YOLOO {

            public  List<String> getOutputNames(Net net) {
                List<String> names = new ArrayList<>();

                List<Integer> outLayers = net.getUnconnectedOutLayers().toList();
                List<String> layersNames = net.getLayerNames();

                outLayers.forEach((item) -> names.add(layersNames.get(item - 1)));
                return names;
            }

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
            String modelWeights = "yolov3.weights";
            String modelConfiguration = "yolov3.cfg";

            Net net = Dnn.readNetFromDarknet(modelConfiguration, modelWeights);
            public LinkedList<Point> getBoxes(Mat aaa , String room) {

                System.loadLibrary(Core.NATIVE_LIBRARY_NAME);


                System.out.println("we reached here");
                Mat image = aaa;
                System.out.println(image.size());
                Size sz = new Size(416, 416);
                Mat blob = Dnn.blobFromImage(image, 0.00392, sz, new Scalar(0), true, false);
                net.setInput(blob);

                List<Mat> result = new ArrayList<>();
                List<String> outBlobNames = getOutputNames(net);

                net.forward(result, outBlobNames);

                outBlobNames.forEach(System.out::println);
                result.forEach(System.out::println);

                float confThreshold = 0.7f;
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
                int counter = 0;
                if(isShow.size()!=0) {

                    // Apply non-maximum suppression procedure.
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
                        if (isShow.get(i)) {
                            counter++;
                            Imgproc.rectangle(image, box.tl(), box.br(), new Scalar(0, 0, 255), 2);
                            coords.add(box.tl());
                            coords.add(box.br());
                            System.out.println(box.tl());
                            System.out.println(box.br());
                        }
                    }
                }

                PrintWriter writer = null;
                try {
                    writer = new PrintWriter("roomcount.txt", "UTF-8");
                } catch (FileNotFoundException e) {
                    System.out.println("sup");
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    System.out.println("supid");
                    e.printStackTrace();
                }

                writer.println(room);

                writer.println(Integer.toString(counter));
                System.out.println(counter);
                writer.close();
                Imgcodecs.imwrite(room+".png", image);
                String command = "firebase.py";
                try {
                    Process p = Runtime.getRuntime().exec("python "+command );
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
            VideoCapture camera = new VideoCapture(0);

            Mat frame = new Mat();



            if(!camera.isOpened()){
                System.out.println("Error");
            }

            else {
                int coutner = 0;
                while(true){

                    if (camera.read(frame)){
                        LinkedList<Point>coords = new YOLOO().getBoxes(frame, room);

                        System.out.println(coords);
                        coutner++;
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


