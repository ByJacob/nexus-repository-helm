/**
 * Configuration for repository storage facet.
 *
 * Create By Jakub Rosa © 2019 Telic AG.
 */
Ext.define('NX.helm.view.repository.recipe.facet.AbsoluteUrlFacet', {
  extend: 'Ext.form.FieldContainer',
  alias: 'widget.nx-coreui-repository-absolute-url-facet',
  requires: [
    'NX.I18n'
  ],

  initComponent: function () {
    var me = this;

    me.items = [
      {
        xtype: 'fieldset',
        cls: 'nx-form-section',
        title: NX.I18n.get('Helm_Hosted_Configuration'),

        defaults: {
          allowBlank: false,
          itemCls: 'required-field'
        },

        items: [
          {
            xtype: 'checkbox',
            name: 'attributes.helm.absoluteUrl',
            itemId: 'absoluteUrl',
            fieldLabel: NX.I18n.get('Absolute_URL_Title'),
            helpText: NX.I18n.get('Absolute_URL_Description'),
            value: false
          }
        ]
      }
    ];

    me.callParent();
  }
});