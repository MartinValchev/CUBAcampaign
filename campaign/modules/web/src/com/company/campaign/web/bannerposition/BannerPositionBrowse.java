package com.company.campaign.web.bannerposition;

import com.company.campaign.entity.BannerPosition;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.components.actions.EditAction;
import com.haulmont.cuba.gui.components.actions.RemoveAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.DataSupplier;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.security.entity.EntityOp;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

public class BannerPositionBrowse extends AbstractLookup {

    /**
     * The {@link CollectionDatasource} instance that loads a list of {@link BannerPosition} records
     * to be displayed in {@link BannerPositionBrowse#bannerPositionsTable} on the left
     */
    @Inject
    private CollectionDatasource<BannerPosition, UUID> bannerPositionsDs;

    /**
     * The {@link Datasource} instance that contains an instance of the selected entity
     * in {@link BannerPositionBrowse#bannerPositionsDs}
     * <p/> Containing instance is loaded in {@link CollectionDatasource#addItemChangeListener}
     * with the view, specified in the XML screen descriptor.
     * The listener is set in the {@link BannerPositionBrowse#init(Map)} method
     */
    @Inject
    private Datasource<BannerPosition> bannerPositionDs;

    /**
     * The {@link Table} instance, containing a list of {@link BannerPosition} records,
     * loaded via {@link BannerPositionBrowse#bannerPositionsDs}
     */
    @Inject
    private Table<BannerPosition> bannerPositionsTable;

    /**
     * The {@link BoxLayout} instance that contains components on the left side
     * of {@link SplitPanel}
     */
    @Inject
    private BoxLayout lookupBox;

    /**
    * The {@link BoxLayout} instance that contains components on the right side
    * of {@link SplitPanel}
    */
    @Inject
    private BoxLayout editBox;

    /**
     * The {@link BoxLayout} instance that contains buttons to invoke Save or Cancel actions in edit mode
     */
    @Inject
    private BoxLayout actionsPane;

    /**
     * The {@link FieldGroup} instance that is linked to {@link BannerPositionBrowse#bannerPositionDs}
     * and shows fields of the selected {@link BannerPosition} record
     */
    @Inject
    private FieldGroup fieldGroup;

    /**
     * The {@link RemoveAction} instance, related to {@link BannerPositionBrowse#bannerPositionsTable}
     */
    @Named("bannerPositionsTable.remove")
    private RemoveAction bannerPositionsTableRemove;

    @Inject
    private DataSupplier dataSupplier;

    /**
     * {@link Boolean} value, indicating if a new instance of {@link BannerPosition} is being created
     */
    private boolean creating;

    @Override
    public void init(Map<String, Object> params) {

        /*
         * Adding {@link com.haulmont.cuba.gui.data.Datasource.ItemChangeListener} to {@link bannerPositionsDs}
         * The listener reloads the selected record with the specified view and sets it to {@link bannerPositionDs}
         */
        bannerPositionsDs.addItemChangeListener(e -> {
            if (e.getItem() != null) {
                BannerPosition reloadedItem = dataSupplier.reload(e.getDs().getItem(), bannerPositionDs.getView());
                bannerPositionDs.setItem(reloadedItem);
            }
        });

        /*
         * Adding {@link CreateAction} to {@link bannerPositionsTable}
         * The listener removes selection in {@link bannerPositionsTable}, sets a newly created item to {@link bannerPositionDs}
         * and enables controls for record editing
         */
        bannerPositionsTable.addAction(new CreateAction(bannerPositionsTable) {
            @Override
            protected void internalOpenEditor(CollectionDatasource datasource, Entity newItem, Datasource parentDs, Map<String, Object> params) {
                bannerPositionsTable.setSelected(Collections.emptyList());
                bannerPositionDs.setItem((BannerPosition) newItem);
                refreshOptionsForLookupFields();
                enableEditControls(true);
            }
        });

        /*
         * Adding {@link EditAction} to {@link bannerPositionsTable}
         * The listener enables controls for record editing
         */
        bannerPositionsTable.addAction(new EditAction(bannerPositionsTable) {
            @Override
            protected void internalOpenEditor(CollectionDatasource datasource, Entity existingItem, Datasource parentDs, Map<String, Object> params) {
                if (bannerPositionsTable.getSelected().size() == 1) {
                    refreshOptionsForLookupFields();
                    enableEditControls(false);
                }
            }

            @Override
            public void refreshState() {
                if (target != null) {
                    CollectionDatasource ds = target.getDatasource();
                    if (ds != null && !captionInitialized) {
                        setCaption(messages.getMainMessage("actions.Edit"));
                    }
                }
                super.refreshState();
            }

            @Override
            protected boolean isPermitted() {
                CollectionDatasource ownerDatasource = target.getDatasource();
                boolean entityOpPermitted = security.isEntityOpPermitted(ownerDatasource.getMetaClass(), EntityOp.UPDATE);
                if (!entityOpPermitted) {
                    return false;
                }
                return super.isPermitted();
            }
        });

        /*
         * Setting {@link RemoveAction#afterRemoveHandler} for {@link bannerPositionsTableRemove}
         * to reset record, contained in {@link bannerPositionDs}
         */
        bannerPositionsTableRemove.setAfterRemoveHandler(removedItems -> bannerPositionDs.setItem(null));

        /*
         * Adding ESCAPE shortcut that invokes cancel() method
         */
        editBox.addShortcutAction(new ShortcutAction(new KeyCombination(KeyCombination.Key.ESCAPE),
        shortcutTriggeredEvent -> cancel()));

        disableEditControls();
    }

    private void refreshOptionsForLookupFields() {
        for (Component component : fieldGroup.getOwnComponents()) {
            if (component instanceof LookupField) {
                CollectionDatasource optionsDatasource = ((LookupField) component).getOptionsDatasource();
                if (optionsDatasource != null) {
                    optionsDatasource.refresh();
                }
            }
        }
    }

    /**
     * Method that is invoked by clicking Ok button after editing an existing or creating a new record
     */
    public void save() {
        if (!validate(Collections.singletonList(fieldGroup))) {
            return;
        }
        getDsContext().commit();

        BannerPosition editedItem = bannerPositionDs.getItem();
        if (creating) {
            bannerPositionsDs.includeItem(editedItem);
        } else {
            bannerPositionsDs.updateItem(editedItem);
        }
        bannerPositionsTable.setSelected(editedItem);

        disableEditControls();
    }

    /**
     * Method that is invoked by clicking Cancel button, discards changes and disables controls for record editing
     */
    public void cancel() {
        BannerPosition selectedItem = bannerPositionsDs.getItem();
        if (selectedItem != null) {
            BannerPosition reloadedItem = dataSupplier.reload(selectedItem, bannerPositionDs.getView());
            bannerPositionsDs.setItem(reloadedItem);
        } else {
            bannerPositionDs.setItem(null);
        }

        disableEditControls();
    }

    /**
     * Enabling controls for record editing
     * @param creating indicates if a new instance of {@link BannerPosition} is being created
     */
    private void enableEditControls(boolean creating) {
        this.creating = creating;
        initEditComponents(true);
        fieldGroup.requestFocus();
    }

    /**
     * Disabling editing controls
     */
    private void disableEditControls() {
        initEditComponents(false);
        bannerPositionsTable.requestFocus();
    }

    /**
     * Initiating edit controls, depending on if they should be enabled/disabled
     * @param enabled if true - enables editing controls and disables controls on the left side of the splitter
     *                if false - visa versa
     */
    private void initEditComponents(boolean enabled) {
        fieldGroup.setEditable(enabled);
        actionsPane.setVisible(enabled);
        lookupBox.setEnabled(!enabled);
    }
}