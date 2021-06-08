package bazar.bs;

import java.util.ArrayList;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import bazar.pojos.Client;
import bazar.pojos.Order;
import bazar.pojos.ProductInCart;

public class SendOrder {
    // Find your Account Sid and Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = System.getenv().get("TwilioAccountSID");
    public static final String AUTH_TOKEN = System.getenv().get("TwilioAuthToken");

    public static Boolean sendOrderMessage(Order order, Client client, ArrayList<ProductInCart> products) {
        Boolean correct = true;
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String msg = "\n" + order.getDate() + "\nCliente: " + client.getName() + " " + client.getSurname() + " "
                + client.getLastname() + "\nOrden:\n";
        for (ProductInCart productInCart : products) {
            msg += "\t*•" + productInCart.getProduct().getName() + " : " + productInCart.getQuantity() + "*\n";
        }
        msg += "Lugar de entrega: \nCalle " + client.getDirection().getStreet() + " numero "
                + client.getDirection().getNumber() + ", Col. " + client.getDirection().getSuburb() + ", Del. "
                + client.getDirection().getTownhall() + ", " + client.getDirection().getState() + ", C.P.: "
                + client.getDirection().getPostalCode();
        msg += "\nMonto de la compra: $" + order.getAmount() + "\n";
        msg += "Comprobante de pago: *152.67.254.94/bazar/" + order.getStatus().replace(" ", "%20") + "*\n";
        msg += "Contacto del cliente: \nTeléfono: " + client.getContact().getPhone() + "\nCorreo: "
                + client.getContact().getEmail();

        Message message = Message.creator(new com.twilio.type.PhoneNumber(System.getenv().get("TwilioDestNum")),
                new com.twilio.type.PhoneNumber(System.getenv().get("TwilioOrigNum")), msg).create();
        System.out.println(message.getSid());
        return correct;
    }
}
