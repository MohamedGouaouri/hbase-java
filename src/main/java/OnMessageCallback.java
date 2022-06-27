import com.google.gson.Gson;
import models.CarInfo;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class OnMessageCallback implements MqttCallback {
    private final Gson g = new Gson();
    HbaseDQL hbaseDQL = new HbaseDQL();

    public void connectionLost(Throwable cause) {
        // After the connection is lost, it usually reconnects here
        System.out.println("disconnect，you can reconnect");
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // The messages obtained after subscribe will be executed here
        String content = new String(message.getPayload());
        CarInfo info = g.fromJson(content, CarInfo.class);
//        hbaseDQL.addEntry(
//                info.matricule,
//                info.generalInfo.speed,
//                info.generalInfo.lat,
//                info.generalInfo.long_,
//                info.engineInfo.temperature,
//                info.engineInfo.state
//                );
        System.out.println(info.matricule);
        System.out.println(info.generalInfo.speed);
        System.out.println(info.generalInfo.lat);
        System.out.println(info.generalInfo.long_);
        System.out.println(info.engineInfo.temperature);
        System.out.println(info.engineInfo.state);

    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }
}