import Position from './Position.js'
import Employee from './Employee.js'

class Organization {
  constructor(root) {

     let numEmployee = 1;
     let pos = Position;

    this.printOrganization = (position, prefix) => {
      let str = `${prefix}+-${position.toString()}\n`;
      for (const p of position.getDirectReports()) {
        str = str.concat(this.printOrganization(p, `${prefix}  `));
      }
      return str;
    };

    // Hire the given person as an employee in the position that has that title
    // Return the newly filled position or undefined if no position has that title
    this.hire = (person, title) => {
      // your code here
      
      // Assign the position to whatever position and title match that the 'findCurrentPosition' helper function finds.
       pos = this.findCurrentPosition(root, title);

      //If position is found, assign that organization position to the user and return it 
       if(pos != null) {
         const newEmployee = new Employee(numEmployee++, person);
         pos.setEmployee(newEmployee);
         return pos;
       }
       return undefined;
    };

    //Uses recursion to go through all the employees and find their current position
    this.findCurrentPosition = (root, title) => {
      //If current user title and title match, return user and move onto next
      if (root.getTitle() === title){
        return root
      } else {
        //else, iterate through direct reports to find a match
        for(const position of root.getDirectReports()) {
          // Calls findCurrentPositiion until organization is traversed
          //Either matching position will be found, or will be left empty
          let PositionFound = this.findCurrentPosition(position, title);
          if(PositionFound == null) {
            continue;
          } else {
            return PositionFound;
          }
        }
      }
      return undefined;
    }

    this.toString = () => this.printOrganization(root, '');
  };
}

export default Organization;