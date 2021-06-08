package bazar.bs;

import java.time.LocalDate;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import bazar.pojos.Client;
import bazar.pojos.Contact;
import bazar.pojos.Direction;
import bazar.pojos.Person;

public class EditProfileBs {

	private static Boolean isAllRequiredFiled(Map<String, String[]> parameterMap) {
		Boolean correct = true;
		if (parameterMap.get("nameTxt")[0].equals("")) {
			correct = false;
		} else if (parameterMap.get("surnameTxt")[0].equals("")) {
			correct = false;
		} else if (parameterMap.get("lastnameTxt")[0].equals("")) {
			correct = false;
		} else if (parameterMap.get("birthdayDate")[0].equals("")) {
			correct = false;
		} else if (parameterMap.get("emailTxt")[0].equals("")) {
			correct = false;
		} else if (parameterMap.get("phoneTxt")[0].equals("")) {
			correct = false;
		} else if (parameterMap.get("streetTxt")[0].equals("")) {
			correct = false;
		} else if (parameterMap.get("numberTxt")[0].equals("")) {
			correct = false;
		} else if (parameterMap.get("suburbTxt")[0].equals("")) {
			correct = false;
		} else if (parameterMap.get("townhallTxt")[0].equals("")) {
			correct = false;
		} else if (parameterMap.get("stateTxt")[0].equals("")) {
			correct = false;
		} else if (parameterMap.get("countryTxt")[0].equals("")) {
			correct = false;
		} else if (parameterMap.get("postalCodeTxt")[0].equals("")) {
			correct = false;
		}
		return correct;
	}

	public static Person getTmpPerson(Map<String, String[]> parameterMap) {
		Contact contact = new Contact(parameterMap.get("phoneTxt")[0], parameterMap.get("emailTxt")[0]);
		Direction direction = new Direction(parameterMap.get("streetTxt")[0],
				Integer.parseInt(parameterMap.get("numberTxt")[0]), parameterMap.get("suburbTxt")[0],
				parameterMap.get("townhallTxt")[0], parameterMap.get("postalCodeTxt")[0],
				parameterMap.get("stateTxt")[0], parameterMap.get("countryTxt")[0]);
		String[] dateParts = parameterMap.get("birthdayDate")[0].split("-");
		LocalDate birthday = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]),
				Integer.parseInt(dateParts[2]));
		return new Person(0, parameterMap.get("nameTxt")[0], parameterMap.get("surnameTxt")[0],
				parameterMap.get("lastnameTxt")[0], birthday, contact, direction);
	}

	private static Boolean passwordsMatch(String pass, String passRepeat) {
		Boolean match = false;
		if (pass.equals(passRepeat)) {
			match = true;
		}
		return match;
	}

	private static Boolean isEmailCorrect(String email) {
		Boolean correct = false;
		Pattern pat = Pattern.compile("^[^@]+@[^@]+\\.[a-zA-Z]{2,}$");
		Matcher mat = pat.matcher(email);
		if (mat.matches()) {
			correct = true;
		}
		return correct;
	}

	private static Boolean isPhoneCorrect(String phone) {
		Boolean correct = false;
		Pattern pat = Pattern.compile("^[2-9]{2}[0-9]{8}$");
		Matcher mat = pat.matcher(phone);
		if (mat.matches()) {
			correct = true;
		}
		return correct;
	}

	public static String getEditMessage(Map<String, String[]> parameterMap, Client originalClient) {
		String message = null;
		if (isAllRequiredFiled(parameterMap)) {
			String email = parameterMap.get("emailTxt")[0];
			if (isEmailCorrect(email)) {
				String phone = parameterMap.get("phoneTxt")[0];
				if (isPhoneCorrect(phone)) {
					String pass = parameterMap.get("passwordTxt")[0];
					String passRepeat = parameterMap.get("repeatPasswordTxt")[0];
					if (!pass.equals("")) {
						if (passwordsMatch(pass, passRepeat)) {
							if (pass.length() >= 8) {
								message = null;
							} else {
								message = "La contraseña debe ser mayor a 8 caracteres";
							}
						} else {
							message = "Las constraseñas no coinciden. Vuelve a intentarlo.";
						}
					}
					String username = parameterMap.get("usernameTxt")[0];
					if (!username.equals("")) {
						Client client = ClientBs.getClientByUsername(username);
						if (client != null) {
							message = "El usuario que estas tratando de ingresar ya existe. Intenta con otro o inicia sesión.";
						}
					}
					if (ClientBs.getClientByEmail(email) == null || email.equals(originalClient.getContact().getEmail())) {
						if (ClientBs.getClientByPhone(phone) == null || phone.equals(originalClient.getContact().getPhone())) {
							message = null;
						} else {
							message = "El teléfono que estás tratando de ingresar ya existe. Intenta con otra o inicia sesión";
						}
					} else {
						message = "El correo electrónico que estás tratando de ingresar ya existe. Intenta con otra o inicia sesión";
					}
				} else {
					message = "Ingresa un numero de teléfono valido.";
				}
			} else {
				message = "Ingresa un correo electrónico valido.";
			}
		} else {
			message = "Asegurate de llenar todos los campos de las secciones obligatorias.";
		}
		return message;
	}
}
