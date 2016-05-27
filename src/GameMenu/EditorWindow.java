package GameMenu;

import javax.swing.*;

public class EditorWindow extends LevelsWindow {

    public EditorWindow(Controller controller) {
        super(controller);
        JButton addLevel = new JButtonWithBackground("C:\\Users\\guyal\\workspace\\UnblockMe\\design\\addnewlevel.jpg");
        addLevel.setActionCommand("Add new level");
        addLevel.addActionListener(controller.new menuPress());
        menuPanel.add(addLevel);
    }

}
