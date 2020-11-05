package jp.jaxa.iss.kibo.rpc.defaultapk;
import gov.nasa.arc.astrobee.Result;
import gov.nasa.arc.astrobee.android.gs.MessageType;
import gov.nasa.arc.astrobee.types.Point;
import gov.nasa.arc.astrobee.types.Quaternion;
import jp.jaxa.iss.kibo.rpc.api.KiboRpcService;
/**
 * Class meant to handle commands from the Ground Data System and execute them in Astrobee
 */
public class YourService extends KiboRpcService {
    private Object JSON;
    //private java.lang.Object Object;
    @Override
    protected void runPlan1() {
        // start this run
        api.judgeSendStart();
        // move Astrobee from the starting point to P1-1
        Point point = new Point(10.95, -3.75, 4.85);
        Quaternion quaternion = new Quaternion(0, 0, 0, -0);
        api.moveTo(point, quaternion, true);
        api.judgeSendStart();
        moveToWrapper(11.5, -5.7, 4.5, 1);
        moveToWrapper(11, -6, 5.55, 0.707106);
        moveToWrapper(11, -5.5, 4.33, 0.7071068);
        moveToWrapper(10.30, -7.5, 4.7, 0);
        moveToWrapper(11.5, -8, 5, 1);
        // turn on the laser light
        api.laserControl(true);
        moveToWrapper(11.5, -5.7, 4.5, 1);
        // take snapshots to evaluate the accuracy of laser pointing and finish this run
        api.judgeSendFinishSimulation();
    }
    private void moveToWrapper(double v, double v1, double v2, double i) {
    }
    @Override
    protected void runPlan2() {
        // write here your plan 2
        //install coordinate of obstacies

        getTrustedRobotKinemeticsKoz(10.75,10.95,-4.9,4.7, 4.8,5.0);
        getTrustedRobotKinemeticsKoz(10.75,11.95,-6.5,-6.4,3.9,5.9);
        getTrustedRobotKinemeticsKoz(9.95,10.85,-7.2,-8.3,3.9,5.9);
        getTrustedRobotKinemeticsKoz(10.10,11.1,-8.6,-8.5,5.4,5.9);
        getTrustedRobotKinemeticsKoz(10.75,11.95,-9.0,-8.6,4.1,5.1);
        getTrustedRobotKinemeticsKoz(10.75,10.45,-9.1,-8.2,.6,5.6);
        getTrustedRobotKinemeticsKoz(10.75,11.15,-8.4,-8.7,4.9,5.1);
        getTrustedRobotKinemeticsKoz(10.75,11.25,-8.9,-8.9,4.2,4.4);
        getTrustedRobotKinemeticsKoz(10.75,0.5,-9.1,-3,4.6,4.8);
        // once Astrobee came to P1-1, get a camera image.
        // read the QR code in the image and get the x-axis coordinate value of P3
        // send the result to scoring module
        String valueX = null;
        api.judgeSendDiscoveredQR(0, valueX);
        api.getBitmapNavCam();
        api.getBitmapDockCam();
        api.getMatNavCam();
        api.getMatDockCam();
        Object MessageType = null;
        sendData(JSON,"data","SUCCESS:defaultapk runPlan2");
    }

    private void getTrustedRobotKinemeticsKoz(Object o, Object o1, Object o2, Object o3, Object o4, Object o5) {
    }

    @Override
    protected void runPlan3() {
        //install coordinate of obstacies
        Object x_min = new Object();
        Object x_max = null;
        String markerId = null;
        getTrustedRobotKinemeticsKiz_Points(x_min);
        // once Astrobee came to P3, get a camera imageuy
        // read the AR tag in the image
        markerId = "";
        // send the result to the scoring module
        api.judgeSendDiscoveredAR(markerId);
        api.getPointCloudHazCam();
        api.getPointCloudPerchCam();
        api.shutdownFactory();
        sendData(JSON, "data", "SUCCESS:defaultapk runPlan3");
    }

    private void getTrustedRobotKinemeticsKiz_Points(Object x_min) {

    }

    private void sendData(Object json, String data, String s) {
    }

    private void getTrustedRobotKinemeticsKiz_Points(java.lang.Object xMin, Object x_min) {
    }

    private void moveToWrapper(double pos_x, double pos_y, double pos_z,
                               double qua_y, double qua_z,
                               double qua_w) {
        final int LOOP_MAX = 3;
        final Point point = new Point(pos_x, pos_y, pos_z);
        final Quaternion quaternion = new Quaternion((float) (double) 0, (float) qua_y,
                (float) qua_z, (float) qua_w);
        Result result = api.moveTo(point, quaternion, true);
        int loopCounter = 0;
        while (!((Result) result).hasSucceeded() || loopCounter < LOOP_MAX) {
            result = api.moveTo(point, quaternion, true);
            ++loopCounter;
        }
    }

}

