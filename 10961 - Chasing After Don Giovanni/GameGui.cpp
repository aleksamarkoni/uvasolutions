//
// Created by aleksamarkoni on 5/19/19.
//

#include <QtGui/QPainter>
#include "GameGui.h"

GameGui::GameGui(QWidget *parent) : QWidget(parent) {

}

GameGui::~GameGui() {
    gameOver = false;
}

void GameGui::paintEvent(QPaintEvent *e) {
    Q_UNUSED(e)

    QPainter painter(this);

    if (gameOver) {

    } else {
        drawObjects(&painter)
    }
}

void GameGui::drawObjects(QPainter *) {

}
