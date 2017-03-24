/*******************************************************************************
 * Copyright (c) 2017 MEDEVIT.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     T. Huster - initial API and implementation
 *******************************************************************************/
package at.medevit.elexis.emediplan.core.model.chmed16a;

import static ch.elexis.core.constants.XidConstants.DOMAIN_EAN;

import java.util.ArrayList;
import java.util.List;

import ch.elexis.data.Anwender;
import ch.elexis.data.Artikel;
import ch.elexis.data.Prescription;

public class Medicament {
	public String Id;
	public int IdType;
	public List<Posology> Pos;
	public String Unit;
	public String TkgRsn;
	public String AppInstr;
	public int AutoMed;
	public String PrscBy;
	public String Roa;
	public int Rep;
	public int Subs;
	public float NbPack;
	public List<PrivateField> PFields;
	
	public static List<Medicament> fromPrescriptions(List<Prescription> prescriptions){
		if (prescriptions != null && !prescriptions.isEmpty()) {
			List<Medicament> ret = new ArrayList<>();
			for (Prescription prescription : prescriptions) {
				Medicament medicament = new Medicament();
				medicament.Unit = "";
				medicament.AutoMed = 0;
				Artikel article = prescription.getArtikel();
				medicament.IdType = getIdType(article);
				medicament.Id = getId(article);
				medicament.Pos = Posology.fromPrescription(prescription);
				String prescriptorId = prescription.get(Prescription.FLD_PRESCRIPTOR);
				medicament.PrscBy = getPrescriptorEAN(prescriptorId);
				ret.add(medicament);
			}
			return ret;
		}
		return null;
	}
	
	private static String getPrescriptorEAN(String prescriptorId){
		if (prescriptorId != null && !prescriptorId.isEmpty()) {
			Anwender prescriptor = Anwender.load(prescriptorId);
			if (prescriptor != null && prescriptor.exists()) {
				String ean = prescriptor.getXid(DOMAIN_EAN);
				if (ean != null && !ean.isEmpty()) {
					return ean;
				}
			}
		}
		return null;
	}
	
	private static int getIdType(Artikel article){
		String gtin = article.getEAN();
		if (gtin != null && !gtin.isEmpty()) {
			return 2;
		}
		String pharma = article.getPharmaCode();
		if (pharma == null || pharma.isEmpty()) {
			pharma = article.get(Artikel.FLD_SUB_ID);
		}
		if (pharma != null && !pharma.isEmpty()) {
			return 3;
		}
		return 1;
	}
	
	private static String getId(Artikel article){
		String gtin = article.getEAN();
		if (gtin != null && !gtin.isEmpty()) {
			return gtin;
		}
		String pharma = article.getPharmaCode();
		if (pharma == null || pharma.isEmpty()) {
			pharma = article.get(Artikel.FLD_SUB_ID);
		}
		if (pharma != null && !pharma.isEmpty()) {
			return pharma;
		}
		throw new IllegalStateException(
			"No ID (GTIN, Pharmacode) for article [" + article.getLabel() + "]");
	}
}
