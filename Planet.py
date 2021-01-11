import numpy as np
from Universe import Universe
class Planet:
    def __init__(self, mass, rev_velocity, pos):
        self.mass = mass
        self.velocity = velocity
        self.pos = pos

    def update_position(self, time):
        self.pos += self.velocity*time

    def update_velocity(self, all_bodies, time):
        for body in all_bodies:
            square_dist = np.linalg.norm(self.position - body.position)
            direction = (self.position - body.position)/square_dist
            force = direction*Universe.gravitational_constant*self.mass*body*mass/square_dist
            acceleration = force/mass
            self.velocity += acceleration*time
