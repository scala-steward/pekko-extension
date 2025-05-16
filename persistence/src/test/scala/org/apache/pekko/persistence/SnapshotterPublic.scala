package org.apache.pekko.persistence

import org.apache.pekko.actor.ActorRef

trait SnapshotterPublic extends Snapshotter {
  def snapshotStore: ActorRef
}
