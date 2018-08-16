/*
 *************************************************************************
 * The contents of this file are subject to the Openbravo  Public  License
 * Version  1.1  (the  "License"),  being   the  Mozilla   Public  License
 * Version 1.1  with a permitted attribution clause; you may not  use this
 * file except in compliance with the License. You  may  obtain  a copy of
 * the License at http://www.openbravo.com/legal/license.html
 * Software distributed under the License  is  distributed  on  an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific  language  governing  rights  and  limitations
 * under the License.
 * The Original Code is Openbravo ERP.
 * The Initial Developer of the Original Code is Openbravo SLU
 * All portions are Copyright (C) 2008-2014 Openbravo SLU
 * All Rights Reserved.
 * Contributor(s):  ______________________________________.
 ************************************************************************
*/
package org.openbravo.model.manufacturing.transaction;

import java.math.BigDecimal;
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.manufacturing.floorshop.Machine;
import org.openbravo.model.materialmgmt.transaction.ProductionPlan;

/**
 * Entity class for entity ManufacturingProductionRunMachine (stored in table MA_PL_Machine).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ProductionRunMachine extends BaseOBObject
		implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "MA_PL_Machine";
    public static final String ENTITY_NAME = "ManufacturingProductionRunMachine";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_PRODUCTIONPLAN = "productionPlan";
    public static final String PROPERTY_MACHINE = "machine";
    public static final String PROPERTY_USAGECOEFFICIENT = "usageCoefficient";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_ESTIMATEDCOST = "estimatedCost";

    public ProductionRunMachine() {
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_USAGECOEFFICIENT, new BigDecimal(1));
        setDefaultValue(PROPERTY_ACTIVE, true);
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }

    public String getId() {
        return (String) get(PROPERTY_ID);
    }

    public void setId(String id) {
        set(PROPERTY_ID, id);
    }

    public Date getCreationDate() {
        return (Date) get(PROPERTY_CREATIONDATE);
    }

    public void setCreationDate(Date creationDate) {
        set(PROPERTY_CREATIONDATE, creationDate);
    }

    public User getCreatedBy() {
        return (User) get( PROPERTY_CREATEDBY);
    }

    public void setCreatedBy(User createdBy) {
        set(PROPERTY_CREATEDBY, createdBy);
    }

    public Date getUpdated() {
        return (Date) get(PROPERTY_UPDATED);
    }

    public void setUpdated(Date updated) {
        set(PROPERTY_UPDATED, updated);
    }

    public User getUpdatedBy() {
        return (User) get( PROPERTY_UPDATEDBY);
    }

    public void setUpdatedBy(User updatedBy) {
        set(PROPERTY_UPDATEDBY, updatedBy);
    }

    public Client getClient() {
        return (Client) get( PROPERTY_CLIENT);
    }

    public void setClient(Client client) {
        set(PROPERTY_CLIENT, client);
    }

    public Organization getOrganization() {
        return (Organization) get( PROPERTY_ORGANIZATION);
    }

    public void setOrganization(Organization organization) {
        set(PROPERTY_ORGANIZATION, organization);
    }

    public ProductionPlan getProductionPlan() {
        return (ProductionPlan) get( PROPERTY_PRODUCTIONPLAN);
    }

    public void setProductionPlan(ProductionPlan productionPlan) {
        set(PROPERTY_PRODUCTIONPLAN, productionPlan);
    }

    public Machine getMachine() {
        return (Machine) get( PROPERTY_MACHINE);
    }

    public void setMachine(Machine machine) {
        set(PROPERTY_MACHINE, machine);
    }

    public BigDecimal getUsageCoefficient() {
        return (BigDecimal) get(PROPERTY_USAGECOEFFICIENT);
    }

    public void setUsageCoefficient(BigDecimal usageCoefficient) {
        set(PROPERTY_USAGECOEFFICIENT, usageCoefficient);
    }

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public BigDecimal getEstimatedCost() {
        return (BigDecimal) get(PROPERTY_ESTIMATEDCOST);
    }

    public void setEstimatedCost(BigDecimal estimatedCost) {
        set(PROPERTY_ESTIMATEDCOST, estimatedCost);
    }

}
