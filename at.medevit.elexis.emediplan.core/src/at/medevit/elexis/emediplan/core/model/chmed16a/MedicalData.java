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

import java.util.List;

public class MedicalData {
	public String DtLstMen;
	public String Prem;
	public String ToG;
	public List<RiskCategory> RG;
	public List<Measurement> Meas;
	public List<PrivateField> PFields;
}
