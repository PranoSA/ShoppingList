package com.compressibleflowcalculator.shopping_api.Views;

public class Item_View {
    public interface ItemCreateView {
    }

    public interface ItemRequestView extends ItemCreateView {
    }

    public interface ItemInternalView extends ItemRequestView {

    }
}
