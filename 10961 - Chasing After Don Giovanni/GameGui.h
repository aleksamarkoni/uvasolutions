//
// Created by aleksamarkoni on 5/19/19.
//

#ifndef INC_10961___CHASING_AFTER_DON_GIOVANNI_GAMEGUI_H
#define INC_10961___CHASING_AFTER_DON_GIOVANNI_GAMEGUI_H

#include <QtWidgets/QWidget>

class GameGui : public QWidget {
public:
    GameGui(QWidget *parent = 0);
    ~GameGui();
protected:
    void paintEvent(QPaintEvent *);
    void drawObjects(QPainter *);
private:
    bool gameOver;
};

#endif //INC_10961___CHASING_AFTER_DON_GIOVANNI_GAMEGUI_H
