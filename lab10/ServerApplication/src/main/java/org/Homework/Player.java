package org.Homework;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Setter
@Getter
class Player {
    private char[][] map = new char[10][10];
    private int[] boats = {4, 3, 2, 1};
    private boolean turn = false;
    private ClientThread self;

    Player(ClientThread self) {
        this.self = self;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = '0';
            }
        }
    }
//L - up R - down U - left D - right

    public String placeBoat(int posX, int posY, char dir, int boatLength) {
        StringBuilder stringBuilder = new StringBuilder();

        if (posX >= 0 && posX < map.length && posY >= 0 && posY < map.length) {
            if (dir == 'L') {
                if (posY - boatLength < 0) {
                    stringBuilder.append("Move not available!");
                } else {
                    boolean isOk = true;
                    for (int i = 0; i < boatLength && isOk; i++) {
                        if (map[posX][posY - i] != '0') {
                            isOk = false;
                        } else if (i == 0 && (posX > 0 && map[posX - 1][posY] != '0' || posX < map.length - 1 && map[posX + 1][posY] != '0')) {
                            isOk = false;
                        } else if (i == boatLength - 1 && (posX > 0 && map[posX - 1][posY - i] != '0' || posX < map.length - 1 && map[posX + 1][posY - i] != '0')) {
                            isOk = false;
                        } else if (posY - i > 0 && map[posX][posY - i - 1] != '0' || posY - i < map.length - 1 && map[posX][posY - i + 1] != '0') {
                            isOk = false;
                        }
                    }
                    if (!isOk) {
                        stringBuilder.append("Move not available!");
                    } else {
                        for (int i = 0; i < boatLength; i++) {
                            map[posX][posY - i] = 'B';
                        }
                        boats[boatLength - 1]--;
                    }
                }
            } else if (dir == 'R') {
                if (posY + boatLength >= map.length) {
                    stringBuilder.append("Move not available!");
                } else {
                    boolean isOk = true;
                    for (int i = 0; i < boatLength && isOk; i++) {
                        if (map[posX][posY + i] != '0') {
                            isOk = false;
                        } else if (i == 0 && (posX > 0 && map[posX - 1][posY] != '0' || posX < map.length - 1 && map[posX + 1][posY] != '0')) {
                            isOk = false;
                        } else if (i == boatLength - 1 && (posX > 0 && map[posX - 1][posY + i] != '0' || posX < map.length - 1 && map[posX + 1][posY + i] != '0')) {
                            isOk = false;
                        } else if (posY + i > 0 && map[posX][posY + i - 1] != '0' || posY + i < map.length - 1 && map[posX][posY + i + 1] != '0') {
                            isOk = false;
                        }
                    }
                    if (!isOk) {
                        stringBuilder.append("Move not available!");
                    } else {
                        for (int i = 0; i < boatLength; i++) {
                            map[posX][posY + i] =

                                    'B';
                        }
                        boats[boatLength - 1]--;
                    }
                }
            } else if (dir == 'U') {
                if (posX - boatLength < 0) {
                    stringBuilder.append("Move not available!");
                } else {
                    boolean isOk = true;
                    for (int i = 0; i < boatLength && isOk; i++) {
                        if (map[posX - i][posY] != '0') {
                            isOk = false;
                        } else if (i == 0 && (posY > 0 && map[posX][posY - 1] != '0' || posY < map.length - 1 && map[posX][posY + 1] != '0')) {
                            isOk = false;
                        } else if (i == boatLength - 1 && (posY > 0 && map[posX - i][posY - 1] != '0' || posY < map.length - 1 && map[posX - i][posY + 1] != '0')) {
                            isOk = false;
                        } else if (posX - i > 0 && map[posX - i - 1][posY] != '0' || posX - i < map.length - 1 && map[posX - i + 1][posY] != '0') {
                            isOk = false;
                        }
                    }
                    if (!isOk) {
                        stringBuilder.append("Move not available!");
                    } else {
                        for (int i = 0; i < boatLength; i++) {
                            map[posX - i][posY] = 'B';
                        }
                        boats[boatLength - 1]--;
                    }
                }
            } else if (dir == 'D') {
                if (posX + boatLength >= map.length) {
                    stringBuilder.append("Move not available!");
                } else {
                    boolean isOk = true;
                    for (int i = 0; i < boatLength && isOk; i++) {
                        if (map[posX + i][posY] != '0') {
                            isOk = false;
                        } else if (i == 0 && (posY > 0 && map[posX][posY - 1] != '0' || posY < map.length - 1 && map[posX][posY + 1] != '0')) {
                            isOk = false;
                        } else if (i == boatLength - 1 && (posY > 0 && map[posX + i][posY - 1] != '0' || posY < map.length - 1 && map[posX + i][posY + 1] != '0')) {
                            isOk = false;
                        } else if (posX + i > 0 && map[posX + i - 1][posY] != '0' || posX + i < map.length - 1 && map[posX + i + 1][posY] != '0') {
                            isOk = false;
                        }
                    }
                    if (!isOk) {
                        stringBuilder.append("Move not available!");
                    } else {
                        for (int i = 0; i < boatLength; i++) {
                            map[posX + i][posY] = 'B';
                        }
                        boats[boatLength - 1]--;
                    }
                }
            }
        } else {
            stringBuilder.append("Move not available!");
        }

        stringBuilder.append("\n");
        for (char[] row : map) {
            stringBuilder.append(new String(row)).append("\n");
        }

        return stringBuilder.toString();
    }


    public String shoot(int x, int y) {
        if (x >= 0 && x < map.length && y >= 0 && y < map.length) {
            if (map[x][y] == 'B') {
                map[x][y] = 'X'; // Mark the hit
                return "Hit!";
            } else if (map[x][y] == '0') {
                map[x][y] = 'M'; // Mark the miss
                return "Miss!";
            } else {
                return "Already shot!";
            }
        } else {
            return "Invalid coordinates!";
        }
    }

    public boolean checkAllSunk() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == 'B') {
                    return false; // Found a boat that's not sunk
                }
            }
        }
        return true; // All boats are sunk
    }

    public String placeBoatAdv(int posX, int posY, Integer isOn, int turn)
    {
        StringBuilder stringBuilder = new StringBuilder();

        return stringBuilder.toString();
    }



    @Override
    public String toString() {
        return "Player{" +
                "map=" + Arrays.toString(map) +
                '}';
    }
}