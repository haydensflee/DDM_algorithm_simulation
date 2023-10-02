# DDM_algorithm_simulation
Development of a software system that can test and compare different methods of distributed decision-making.

## Project Vision 
The project vision, as described in the initial report, was to create a user-friendly software system which can test different distributed decision-making (DDM) algorithms in a range of specified scenarios with a graphical interface. The final product managed to fulfil this vision and meet most of the expectations that were presented. These included having a modular implementation, capability to run scenarios with varying parameters to assess their effect on performance, and geo-spatial visualisation. Unfortunately due to time constraints, only one distributed decision-making (DDM) algorithm was implemented, so the software system’s ability to compare effectiveness could not be demonstrated. However, appropriate performance metrics were still considered, and a method for comparing simulation outputs in a time-series display was developed. The project goal of producing modular, extensible, and scalable code was semi-fulfilled. The application frontend was built using JavaSwing, and features easy-to-use scenario generation tools. This is decoupled from the system backend, which takes advantage of the open-source Java Multi-Agent Simulation Of Neighbourhoods (MASON) framework to handle the simulation logic. This decoupling allows for future substitution of a different front/backend whilst still maintaining compatibility, and also facilitates easy integration of additional DDM behaviours once they have been developed.

## Architecture

![image](https://github.com/haydensflee/DDM_algorithm_simulation/assets/89950637/7b8e5363-049c-44d2-8af2-337f862c2dcd)

The project architecture consisted of a layered organisational style, an object-oriented decomposition style, and an event-driven (broadcast) model. The software system naturally fit a layered organisational style as the design principles needed to revolve around the agent-based simulation framework that the development team would decide on. If the simulation framework acted as the system’s business logic layer, it would require support from a presentation layer consisting of a user interface, and a data access layer that contained all the underlying information required to run the simulation. To facilitate a simulation of agents with varying parameters, it also made sense to take advantage of an object-oriented decomposition style. By creating each agent as an object and maintaining a record of object lists, the models can be implemented across all layers of the software system with minimal complexity. Lastly, the broadcast model was utilised as the software system control style. This is entirely controlled by the coordinator, which acts as the single mechanism for manipulating the SimState as it is always aware of the entire software system state. The control flow between subsystems is entirely managed by system events. This primarily included clicking on buttons, selecting agents on the user interface, and
a1734056 – Hayden Lee Final Report Software Engineering & Project
Final Report – SEP 2022 pg. 8
simulation events completing. An event-based control style was easy to implement; however, it will be important to ensure that future subsystems are managed by the coordinator appropriately.

**Frontend User Interface**

![image](https://github.com/haydensflee/DDM_algorithm_simulation/assets/89950637/fabbcb14-55ff-4443-92d3-63df25e4267a)

The UI was designed in conjunction with the user stories that were provided by the product owner (details given in report). From left to right, the three main panels are:
- Agent Map: The view of the scenario. Visually shows the position of the aliens (red), cowboys (blue), and target (green) on a 100unit x 100unit map.
- Agent List: An interactive list of agents. The user can select agents of interest and view/manipulate parameters in the agent properties panel.
- Agent Properties: Contains chgeck boxes, text edit boxes, sliders, and buttons for the user to adjust the currently selected agent.
Along the bottom of the UI is an area dedicated to simulation information. The user can select whether they want to see the simulation in real time, which DDM algorithm they wish to use, and buttons for config file generation/running the simulation.

Some additional screenshots of the simulation and metrics analysis screens have been provided. DDM_Demo.mp4 gives a demonstration of the program in action.

**Simulation**

![image](https://github.com/haydensflee/DDM_algorithm_simulation/assets/89950637/e50e9f30-7961-44cc-bf2e-13c6eeaeecf0)

**Metrics**

![image](https://github.com/haydensflee/DDM_algorithm_simulation/assets/89950637/e1673383-676d-49e0-acbc-72604bb4cd96)
