package com.aa.act.interview.org;

import java.util.Optional;

public abstract class Organization {

	private Position root;
	// Set the first number of employee to one
	private int numEmployee = 1;

	public Organization() {
		root = createOrganization();
	}

	protected abstract Position createOrganization();

	/**
	 * hire the given person as an employee in the position that has that title
	 * 
	 * @param person
	 * @param title
	 * @return the newly filled position or empty if no position has that title
	 */

	/**
	 * Steps of 'hire' function:
	 * Use 'positionFinder' Helper function to find the desired position
	 * If position was found, assign person to that position and move to next person
	 * If none was found, return empty position
	 */

	public Optional<Position> hire(Name person, String title) {

		// Search through organization and find position
		Position position = positionFinder(this.root, title);

		// If a position is foound:
		if (position != null) {
			Optional<Employee> newEmployee = Optional.of(new Employee(this.numEmployee++, person));
			position.setEmployee(newEmployee);
			return Optional.of(position);
		}

		// One returns if match is not found
		return Optional.empty();
	}

	private Position positionFinder(Position root, String title) {
		// If the position of the current user is found in the organization
		if (root.getTitle().equals(title)) {
			return root;
		} else {
			// If pos is not found initially, search through the direct reports of the
			// current pos
			for (Position position : root.getDirectReports()) {
				// Call position finder until organization is traversed and either position is
				// found, or empty (recursion)
				Position PositionFound = positionFinder(position, title);
				// if position was not found after org is traversed
				if (PositionFound == null) {
					continue;
				} else {
					return PositionFound;
				}
			}
		}

		// Returns null if persons position is not found in the tree
		return null;
	}

	@Override
	public String toString() {
		return printOrganization(root, "");
	}

	private String printOrganization(Position pos, String prefix) {
		StringBuffer sb = new StringBuffer(prefix + "+-" + pos.toString() + "\n");
		for (Position p : pos.getDirectReports()) {
			sb.append(printOrganization(p, prefix + "\t"));
		}
		return sb.toString();
	}
}