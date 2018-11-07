package com.vitor.mars;

public class Rover {

	private static final String NORTH = "N";
	private static final String SOUTH = "S";
	private static final String EAST = "E";
	private static final String WEST = "W";
	private static final int LEFT = 1;
	private static final int RIGHT = 2;

	private int x, y;
	private String heading;
	private Plateau plateau;

	public Rover(final int x, final int y, final String heading) {
		this.x = x;
		this.y = y;
		this.heading = heading;
		System.out.println("Rover created: " + this.getCurrentPosition());
	}

	public void execute(final String commands) throws Exception {
		if (commands == null || commands.trim().equals(""))
			return;

		for (int i = 0; i < commands.length(); i++) {
			// commands that change heading
			// commands that move to heading
			switch (commands.charAt(i)) {
			case 'L':
				this.changeHeadingTo(LEFT);
				break;
			case 'R':
				this.changeHeadingTo(RIGHT);
				break;
			case 'M':
				this.moveForward();
				break;
			default:
				System.out.println("Unknown command! " + commands.charAt(i));
				break;
			}
		}
	}

	private void moveForward() throws Exception {
		int _x = this.x, _y = this.y;

		switch (this.heading) {
		case NORTH:
			_y++;
			break;

		case EAST:
			_x++;
			break;

		case SOUTH:
			_y--;
			break;

		case WEST:
			_x--;
			break;
		}

		if (this.newPositionIsValid(_x, _y)) {
			this.x = _x;
			this.y = _y;

			// if everything is ok tell plateu to allocate that position
			// TODO notificate plateau

		} else
			throw new Exception("Invalid position!");
	}

	private boolean newPositionIsValid(final int _x, final int _y) {
		// check if it's not leaving the plateau
		if (this.invalidX(_x) || this.invalidY(_y))
			return false;

		if (this.plateau.hasRover(_x, _y))
			return false;

		return true;
	}

	private boolean invalidY(final int _y) {
		return _y > this.plateau.getY() || _y < 0;
	}

	private boolean invalidX(final int _x) {
		return _x > this.plateau.getX() || _x < 0;
	}

	/**
	 * @param direction
	 *            left or right
	 */
	private void changeHeadingTo(final int direction) {
		switch (this.heading) {
		case NORTH:
			this.handleNorth(direction);
			break;
		case EAST:
			this.handleEast(direction);
			break;
		case SOUTH:
			this.handleSouth(direction);
			break;
		case WEST:
			this.handleWest(direction);
			break;
		}
	}

	private void handleWest(final int direction) {
		if (direction == LEFT)
			this.heading = SOUTH;
		else if (direction == RIGHT)
			this.heading = NORTH;
	}

	private void handleSouth(final int direction) {
		if (direction == LEFT)
			this.heading = EAST;
		else if (direction == RIGHT)
			this.heading = WEST;
	}

	private void handleEast(final int direction) {
		if (direction == LEFT)
			this.heading = NORTH;
		else if (direction == RIGHT)
			this.heading = SOUTH;
	}

	private void handleNorth(final int direction) {
		if (direction == LEFT)
			this.heading = WEST;
		else if (direction == RIGHT)
			this.heading = EAST;
	}

	public String getCurrentPosition() {
		return this.x + " " + this.y + " " + this.heading;
	}

	public void landOn(final Plateau plateau) {
		this.plateau = plateau;
	}
}
