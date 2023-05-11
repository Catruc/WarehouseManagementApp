package Presentation;

import Presentation.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private View view;

    public Controller(View view) {
        this.view = view;
        initListeners();
    }

    private void initListeners() {
        view.getClientButton().addActionListener(new ClientButtonListener());
        view.getProductButton().addActionListener(new ProductButtonListener());
        view.getOrderButton().addActionListener(new OrderButtonListener());
//        view.getClientInsertButton().addActionListener(new ClientInsertButtonListener());
//        view.getDeleteClientButton().addActionListener(new DeleteClientButtonListener());
//        view.getUpdateClientButton().addActionListener(new UpdateClientButtonListener());
        view.getBackToWelcomeButton().addActionListener(new BackToWelcomeButtonListener());
        view.getBackToWelcomeButtonFromOrder().addActionListener(new BackToWelcomeButtonListenerFromOrders());
        view.getBackToWelcomeButtonFromProduct().addActionListener(new BackToWelcomeButtonListenerFromProducts());
    }

    class ClientButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getWelcomeFrame().setVisible(false);
            view.getClientFrame().setVisible(true);

        }
    }

    class BackToWelcomeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getClientFrame().setVisible(false);
            view.getWelcomeFrame().setVisible(true);
        }
    }

    class OrderButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getWelcomeFrame().setVisible(false);
            view.getOrderFrame().setVisible(true);
        }
    }

    class BackToWelcomeButtonListenerFromOrders implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getOrderFrame().setVisible(false);
            view.getWelcomeFrame().setVisible(true);
        }
    }

    class BackToWelcomeButtonListenerFromProducts implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getProductFrame().setVisible(false);
            view.getWelcomeFrame().setVisible(true);
        }
    }

    class ProductButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getWelcomeFrame().setVisible(false);
            view.getProductFrame().setVisible(true);
        }
    }
}
