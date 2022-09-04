package ch.elexis.ebanking.qr;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

import ch.elexis.core.model.IPerson;
import ch.elexis.core.types.Gender;
import ch.rgw.tools.TimeTool;

public class PersonFormatUtil {

	private static DateTimeFormatter defaultDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	/**
	 * Get the date of birth of the person in the default date format (Ger.
	 * dd.MM.yyyy).
	 *
	 * @param person
	 * @return
	 */
	public static String getDateOfBirth(IPerson person) {
		TimeTool dob = person.getDateOfBirth();
		if (dob != null) {
			return dob.toString(TimeTool.DATE_GER_SHORT);
		}
		return "";
	}

	/**
	 * Get the gender of the person as localized char.
	 *
	 * @param person
	 * @return
	 */
	public static char getGenderCharLocalized(IPerson person) {
		Gender gender = person.getGender();
		if (gender != null) {
			if (Gender.MALE == gender) {
				return 'm';
			}
			if (Gender.FEMALE == gender) {
				return 'w';
			}
		}
		return '?';
	}

	/**
	 * Get the personalia text for a person. </br>
	 * If the person has a title it is appended to the end of the text, for name
	 * searches.
	 *
	 * @param person
	 * @return
	 */
	public static String getPersonalia(IPerson person) {
		StringBuilder sb = new StringBuilder(64);

		if (StringUtils.isNoneEmpty(person.getFamilyName())) {
			sb.append(person.getFamilyName());
		}
		if (StringUtils.isNotBlank(sb.toString())) {
			sb.append(" ");
		}
		if (StringUtils.isNoneEmpty(person.getFirstName())) {
			sb.append(person.getFirstName());
		}

		if (getGenderCharLocalized(person) != '?') {
			sb.append(" (").append(getGenderCharLocalized(person)).append("), ");
		}

		String dob = getDateOfBirth(person);
		if (StringUtils.isNoneEmpty(dob)) {
			sb.append(dob);
		}

		if (StringUtils.isNoneEmpty(person.getTitel())) {
			sb.append(",").append(person.getTitel());
		}
		return sb.toString();
	}

	public static String getFullName(IPerson person) {
		StringBuilder sb = new StringBuilder(64);

		if (StringUtils.isNoneEmpty(person.getTitel())) {
			sb.append(person.getTitel());
		}
		if (StringUtils.isNotBlank(sb.toString())) {
			sb.append(" ");
		}
		if (StringUtils.isNoneEmpty(person.getFamilyName())) {
			sb.append(person.getFamilyName());
		}
		if (StringUtils.isNotBlank(sb.toString())) {
			sb.append(" ");
		}
		if (StringUtils.isNoneEmpty(person.getFirstName())) {
			sb.append(person.getFirstName());
		}
		return sb.toString();
	}

	public static String getSalutation(IPerson person) {
		if (person.getGender().equals(Gender.MALE)) {
			return "Herr";
		} else if (person.getGender().equals(Gender.FEMALE)) {
			return "Frau";
		} else {
			return ""; //$NON-NLS-1$
		}
	}
}
