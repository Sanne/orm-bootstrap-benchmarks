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
package org.openbravo.model.financialmgmt.payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.financialmgmt.accounting.Costcenter;
import org.openbravo.model.financialmgmt.accounting.UserDimension1;
import org.openbravo.model.financialmgmt.accounting.UserDimension2;
import org.openbravo.model.financialmgmt.assetmgmt.Asset;
import org.openbravo.model.marketing.Campaign;
import org.openbravo.model.materialmgmt.cost.ABCActivity;
import org.openbravo.model.project.Project;

/**
 * Entity class for entity FIN_Doubtful_Debt (stored in table FIN_Doubtful_Debt).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class DoubtfulDebt extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "FIN_Doubtful_Debt";
    public static final String ENTITY_NAME = "FIN_Doubtful_Debt";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_FINPAYMENTSCHEDULE = "fINPaymentSchedule";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_AMOUNT = "amount";
    public static final String PROPERTY_POSTED = "posted";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_FINDOUBTFULDEBTRUN = "fINDoubtfulDebtRun";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_ACTIVITY = "activity";
    public static final String PROPERTY_SALESCAMPAIGN = "salesCampaign";
    public static final String PROPERTY_PROJECT = "project";
    public static final String PROPERTY_COSTCENTER = "costCenter";
    public static final String PROPERTY_STDIMENSION = "stDimension";
    public static final String PROPERTY_NDDIMENSION = "ndDimension";
    public static final String PROPERTY_APRMPROCESS = "aPRMProcess";
    public static final String PROPERTY_ACCOUNTINGDATE = "accountingDate";
    public static final String PROPERTY_ASSET = "asset";
    public static final String PROPERTY__COMPUTEDCOLUMNS = "_computedColumns";
    public static final String PROPERTY_FINDOUBTFULDEBTVLIST = "fINDoubtfulDebtVList";


    // Computed columns properties, these properties cannot be directly accessed, they need
    // to be read through _commputedColumns proxy. They cannot be directly used in HQL, OBQuery
    // nor OBCriteria. 
    public static final String COMPUTED_COLUMN_OUTSTANDINGDOUBTFULDEBTAMOUNT = "outstandingDoubtfulDebtAmount";

    public DoubtfulDebt() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_AMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_POSTED, "N");
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_APRMPROCESS, "P");
        setDefaultValue(PROPERTY_FINDOUBTFULDEBTVLIST, new ArrayList<Object>());
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

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
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

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public Currency getCurrency() {
        return (Currency) get( PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public FIN_PaymentSchedule getFINPaymentSchedule() {
        return (FIN_PaymentSchedule) get( PROPERTY_FINPAYMENTSCHEDULE);
    }

    public void setFINPaymentSchedule(FIN_PaymentSchedule fINPaymentSchedule) {
        set(PROPERTY_FINPAYMENTSCHEDULE, fINPaymentSchedule);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public BigDecimal getAmount() {
        return (BigDecimal) get(PROPERTY_AMOUNT);
    }

    public void setAmount(BigDecimal amount) {
        set(PROPERTY_AMOUNT, amount);
    }

    public String getPosted() {
        return (String) get(PROPERTY_POSTED);
    }

    public void setPosted(String posted) {
        set(PROPERTY_POSTED, posted);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public DoubtfulDebtRun getFINDoubtfulDebtRun() {
        return (DoubtfulDebtRun) get( PROPERTY_FINDOUBTFULDEBTRUN);
    }

    public void setFINDoubtfulDebtRun(DoubtfulDebtRun fINDoubtfulDebtRun) {
        set(PROPERTY_FINDOUBTFULDEBTRUN, fINDoubtfulDebtRun);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get( PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public ABCActivity getActivity() {
        return (ABCActivity) get( PROPERTY_ACTIVITY);
    }

    public void setActivity(ABCActivity activity) {
        set(PROPERTY_ACTIVITY, activity);
    }

    public Campaign getSalesCampaign() {
        return (Campaign) get( PROPERTY_SALESCAMPAIGN);
    }

    public void setSalesCampaign(Campaign salesCampaign) {
        set(PROPERTY_SALESCAMPAIGN, salesCampaign);
    }

    public Project getProject() {
        return (Project) get( PROPERTY_PROJECT);
    }

    public void setProject(Project project) {
        set(PROPERTY_PROJECT, project);
    }

    public Costcenter getCostCenter() {
        return (Costcenter) get(PROPERTY_COSTCENTER);
    }

    public void setCostCenter(Costcenter costCenter) {
        set(PROPERTY_COSTCENTER, costCenter);
    }

    public UserDimension1 getStDimension() {
        return (UserDimension1) get(PROPERTY_STDIMENSION);
    }

    public void setStDimension(UserDimension1 stDimension) {
        set(PROPERTY_STDIMENSION, stDimension);
    }

    public UserDimension2 getNdDimension() {
        return (UserDimension2) get( PROPERTY_NDDIMENSION);
    }

    public void setNdDimension(UserDimension2 ndDimension) {
        set(PROPERTY_NDDIMENSION, ndDimension);
    }

    public String getAPRMProcess() {
        return (String) get(PROPERTY_APRMPROCESS);
    }

    public void setAPRMProcess(String aPRMProcess) {
        set(PROPERTY_APRMPROCESS, aPRMProcess);
    }

    public Date getAccountingDate() {
        return (Date) get(PROPERTY_ACCOUNTINGDATE);
    }

    public void setAccountingDate(Date accountingDate) {
        set(PROPERTY_ACCOUNTINGDATE, accountingDate);
    }

    public Asset getAsset() {
        return (Asset) get( PROPERTY_ASSET);
    }

    public void setAsset(Asset asset) {
        set(PROPERTY_ASSET, asset);
    }

    public BigDecimal getOutstandingDoubtfulDebtAmount() {
        return (BigDecimal) get(COMPUTED_COLUMN_OUTSTANDINGDOUBTFULDEBTAMOUNT);
    }

    public void setOutstandingDoubtfulDebtAmount(BigDecimal outstandingDoubtfulDebtAmount) {
        set(COMPUTED_COLUMN_OUTSTANDINGDOUBTFULDEBTAMOUNT, outstandingDoubtfulDebtAmount);
    }

    public DoubtfulDebt_ComputedColumns get_computedColumns() {
        return (DoubtfulDebt_ComputedColumns) get( PROPERTY__COMPUTEDCOLUMNS);
    }

    public void set_computedColumns(DoubtfulDebt_ComputedColumns _computedColumns) {
        set(PROPERTY__COMPUTEDCOLUMNS, _computedColumns);
    }

    @SuppressWarnings("unchecked")
    public List<DoubtfulDebtV> getFINDoubtfulDebtVList() {
      return (List<DoubtfulDebtV>) get(PROPERTY_FINDOUBTFULDEBTVLIST);
    }

    public void setFINDoubtfulDebtVList(List<DoubtfulDebtV> fINDoubtfulDebtVList) {
        set(PROPERTY_FINDOUBTFULDEBTVLIST, fINDoubtfulDebtVList);
    }


    @Override
    public Object get(String propName) {
      if (COMPUTED_COLUMN_OUTSTANDINGDOUBTFULDEBTAMOUNT.equals(propName)) {
        if (get_computedColumns() == null) {
          return null;
        }
        return get_computedColumns().getOutstandingDoubtfulDebtAmount();
      }
    
      return super.get(propName);
    }
}
